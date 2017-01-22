package com.tinkergration;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
//	
//	private enum Position {
//		BlockX, BlockY, BlockZ,
//		ChunkX, ChunkY, ChunkZ,
//		RegionX, RegionY, RegionZ
//	}
//	
//	private int[] positions	= new int[Position.values().length];
	
	int blockX, blockY, blockZ = 0;
	int chunkX, chunkY, chunkZ = 0;
	int regionX, regionY, regionZ = 0;
    
//    public Vec nextPosition(){
//        goToNextPosition();
//
//        return new Vec(
//            blockX+(16*chunkX)+(256*regionX),
//            blockY+(16*chunkY)+(256*regionY),
//            blockZ+(16*chunkZ)+(256*regionZ));
//    }
    
    private void goToNextPositionBitwise() {
    	
    	if ((blockX = ((blockX + 1) & ~16)) != 0) { return; }
    	if ((blockY = ((blockY + 1)& ~16)) != 0) { return; }
    	if ((blockZ = ((blockZ + 1) & ~16)) != 0) { return; }
    	
    	if ((chunkX = ((chunkX + 1)& ~16)) != 0) { return; }
    	if ((chunkY = ((chunkY + 1) & ~16)) != 0) { return; }
    	if ((chunkZ = ((chunkZ + 1) & ~16)) != 0) { return; }
    	
    	if ((regionX = ((regionX + 1)& ~16)) != 0) { return; }
    	if ((regionY = ((regionY + 1) & ~16)) != 0) { return; }
    	
    	regionX = (regionZ + 1) & ~16;
    }
    
    private int blockCounter = 0;
    private int regionCounter = 0;
    
    private void goToNextPositionBitwise2() {
    	blockCounter++;
    	blockCounter &= 251658240;
    	
    	blockX = blockCounter & 15;
    	blockY = (blockCounter & 240) >> 4;
    	blockZ = (blockCounter & 3840) >> 8;
    	
    	chunkX = (blockCounter & 61440) >> 12;
    	chunkY = (blockCounter & 983040) >> 16;
    	chunkZ = (blockCounter & 15728640) >> 20;
    	
    	
//    	regionX = blockCounter & 251658240 >> 24;
    	
    	
    	
//    	regionX = regionCounter & 15;
//    	regionY = (regionCounter & 240) >> 4;
//    	regionZ = (regionCounter & 3840) >> 8;
    	//regionY = blockCounter & 4026531840 >> 28;
    	//regionZ = blockCounter & 64424509440 >> 32;
    	
//    	if ((blockX = ((blockX + 1) & ~16)) != 0) { return; }
//    	if ((blockY = ((blockY + 1)& ~16)) != 0) { return; }
//    	if ((blockZ = ((blockZ + 1) & ~16)) != 0) { return; }
//    	
//    	if ((chunkX = ((chunkX + 1)& ~16)) != 0) { return; }
//    	if ((chunkY = ((chunkY + 1) & ~16)) != 0) { return; }
//    	if ((chunkZ = ((chunkZ + 1) & ~16)) != 0) { return; }
//    	
//    	if ((regionX = ((regionX + 1)& ~16)) != 0) { return; }
//    	if ((regionY = ((regionY + 1) & ~16)) != 0) { return; }
//    	
//    	regionX = (regionZ + 1) & ~16;
    }
    	
    private void goToNextPositionModulo() {
    	
    	if ((blockX = ((blockX + 1) % 16)) != 0) { return; }
    	if ((blockY = ((blockY + 1) % 16)) != 0) { return; }
    	if ((blockZ = ((blockZ + 1) % 16)) != 0) { return; }
    	
    	if ((chunkX = ((chunkX + 1)% 16)) != 0) { return; }
    	if ((chunkY = ((chunkY + 1) % 16)) != 0) { return; }
    	if ((chunkZ = ((chunkZ + 1) % 16)) != 0) { return; }
    	
    	if ((regionX = ((regionX + 1)% 16)) != 0) { return; }
    	if ((regionY = ((regionY + 1) % 16)) != 0) { return; }
    	
    	regionX = (regionZ + 1) & ~15;
    }
    
    private void goToNextPositionIf() {
    	if (++blockX==16){
            blockX=0;
            if (++blockY==16){
                blockY=0;
                if (++blockZ==16){
                    blockZ=0;
                    if (++chunkX==16){
                        chunkX=0;
                        if (++chunkY==16){
                            chunkY=0;
                            if (++chunkZ==16){
                                chunkZ=0;
                                if (++regionX==16){
                                    regionX=0;
                                    if (++regionY==16){
                                        regionY=0;
                                        regionZ++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
	public static void main(String[] args) {
		Test test = new Test();
		long time = System.nanoTime();
		for (int i = 0; i < 10000000; i++) {
			test.goToNextPositionIf();
		}
		long after = System.nanoTime();

		System.out.println("If: " + (after-time) / 1000 + "ms");
		
		test = new Test();
		time = System.nanoTime();
		for (int i = 0; i < 10000000; i++) {
			test.goToNextPositionModulo();
		}
		after = System.nanoTime();

		System.out.println("Modulo: " + (after-time) / 1000 + "ms");
		
		test = new Test();
		time = System.nanoTime();
		for (int i = 0; i < 10000000; i++) {
			test.goToNextPositionBitwise();
		}
		after = System.nanoTime();
		
		System.out.println("Bitwise: " + (after-time) / 1000 + "ms");
		
		test = new Test();
		time = System.nanoTime();
		for (int i = 0; i < 10000000; i++) {
			test.goToNextPositionBitwise2();
		}
		after = System.nanoTime();
		
		System.out.println("Bitwis2e: " + (after-time) / 1000 + "ms");
	}

}
