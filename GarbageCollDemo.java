public class GarbageCollDemo {
    int mem = 30000;
    int [] Array = new int [mem];
    void occpyMemory(){
        for(int i=0;i<mem;i++){
            Array[i]=i;
        }
    }
    void discardArray(){
        for(int i=0;i<mem;i++){
            Array[i]=0;
        }
    }

    public static void main(String[] args) {
        GarbageCollDemo gc = new GarbageCollDemo();
        Runtime r = Runtime.getRuntime();
        long freememory = r.freeMemory();
        System.out.println("Initial memory before creating array "+freememory);
        r.gc();
        freememory = r.freeMemory();
        System.out.println("Free memory after garbage collection "+freememory);
        gc.occpyMemory();
        freememory = r.freeMemory();
        System.out.println("Free memory after creating array"+freememory);
        gc.discardArray();
        r.gc();
        freememory = r.freeMemory();
        System.out.println("Free memory after running gc ():"+freememory);
    }
}
