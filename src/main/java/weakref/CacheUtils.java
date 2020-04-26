package weakref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 利用map作为高速缓存，但是通过软引用的方式，如果软引用被gc回收就会出现在refqueue中，这是从map(模拟缓存中）清楚该数据
 * 实现了高速缓存的简单模型。
 *
 * 如果是强引用内存一直不被回收可能导致OOM异常
 * @param <K>
 * @param <V>
 */

public class CacheUtils<K,V> {

    //基于软引用实现的缓存，当内存不够使会自动释放缓存内容，以避免OOM
    private ConcurrentHashMap<K,InnerSoftReference<V>> cacheMap;

    // 引用队列，当GC执行后被回收的缓存对象的软引用将被入队，以方便从缓存池中清除失效的软引用。
    private ReferenceQueue<V> referenceQueue;//

    public CacheUtils(){
        cacheMap = new ConcurrentHashMap<K,InnerSoftReference<V>>();
        referenceQueue = new ReferenceQueue<V>();
    }

    /**
     * 往缓存添加对象
     * @param key
     * @param value
     */
    public void put(K key,V value){
        cacheMap.put(key, new InnerSoftReference<V>(key, value, referenceQueue));
        //清除垃圾引用
        clearInvalidReference();
    }


    /**
     * 从缓存中获取channel
     * @return
     */
    public V get(K key){
        synchronized (CacheUtils.class) {
            InnerSoftReference<V> ref = cacheMap.get(key);
            if ( ref != null ){
                return ref.get();
            }
        }
        return null;
    }

    public boolean contain(K key){
        return cacheMap.containsKey(key);
    }

    /**
     * 获取缓存大小
     * @return
     */
    public int size(){
        return cacheMap.size();
    }

    /**
     * 清除失效的软引用
     */
    @SuppressWarnings("unchecked")
    private void clearInvalidReference(){
        InnerSoftReference<V> innerSoftReference;
        while((innerSoftReference = (InnerSoftReference<V>) referenceQueue.poll()) != null){
            cacheMap.remove(innerSoftReference.getKey());//删除掉无效的软引用

        }
    }


    /**
     * 清空缓存
     */
    public void clear(){
        cacheMap.clear();
        clearInvalidReference();
    }



    private class InnerSoftReference<V> extends SoftReference<V>{

        private K key;
        public InnerSoftReference(K key,V value,ReferenceQueue<V> referenceQueue){
            super(value, referenceQueue);
            this.key = key;
        }

        public K getKey() {
            return key;
        }

    }

}

