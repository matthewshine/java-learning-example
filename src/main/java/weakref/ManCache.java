package weakref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

public class ManCache {

    private static ManReference manReference;

    private static ReferenceQueue<Man> referenceReferenceQueue;

    private static ConcurrentHashMap<Integer, ManReference> cacheMap;

    private ManCache() {
        referenceReferenceQueue = new ReferenceQueue<Man>();
    }

    private static class ManCacheSingle {
        static ManCache manCache = new ManCache();
    }

    public static ManCache getManCahce() {
        return ManCacheSingle.manCache;
    }

    public static void cacheInto(Man man) {
        cacheClean();
        int id = man.getId();
        ManReference manReference = new ManReference(man);
        cacheMap.put(id, manReference);
    }

    public static void cacheClean() {
        ManReference manReference;
        //如果对象被回收，那么就把这个对象在map里面对应的值删除
        while ((manReference = (ManReference) referenceReferenceQueue.poll()) == null) {
            int id = manReference.id;
            cacheMap.remove(id);
        }
    }

    public static Man getMan(Integer id) throws RuntimeException {
        check(id);
        Man man = cacheMap.get(id).get();
        if (man == null) {
            man = getFromDatabase(id);
            cacheInto(man);
        }
        return man;
    }


    private static class ManReference extends SoftReference<Man> {
        int id;

        public ManReference(Man referent) {
            super(referent, referenceReferenceQueue);
            id = referent.getId();
        }
    }


    private static void check(Integer id) {
        if (id == null) {
            throw new RuntimeException("id不可以为null");
        }
    }

    /**
     * 数据库取值
     *
     * @param id
     * @return
     */
    private static Man getFromDatabase(Integer id) {
        return new Man();
    }

}