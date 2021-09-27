#  集合
  为什么要在计算机中引入集合呢？这是为了便于处理一组类似的数据
 - list 有序列表
 - set 无序列表
    -  用于存储不重复的元素集合
    Set接口并不保证有序，而SortedSet接口则保证元素是有序的：
       - HashSet是无序的，因为它实现了Set接口，并没有实现SortedSet接口；
       - TreeSet是有序的，因为它实现了SortedSet接口。
 - map 键值映射表
   ```$xslt
       使用Map的时候，只要key不相同，它们映射的value就互不干扰。但是，在HashMap内部，确实可能存在不同的key，映射到相同的hashCode()，即相同的数组索引上，肿么办？
       我们就假设"a"和"b"这两个key最终计算出的索引都是5，那么，在HashMap的数组中，实际存储的不是一个Person实例，而是一个List，它包含两个Entry，一个是"a"的映射，一个是"b"的映射
       
       对于Person p = map.get("a");HashMap内部通过"a"找到的实际上是List<Entry<String, Person>>，它还需要遍历这个List，并找到一个Entry，它的key字段是"a"，才能返回对应的Person实例。
       我们把不同的key具有相同的hashCode()的情况称之为哈希冲突。在冲突的时候，一种最简单的解决办法是用List存储hashCode()相同的key-value。显然，如果冲突的概率越大，这个List就越长，Map的get()方法效率就越低
    ```
   - 正确使用Map必须保证：
      1. 作为key的对象必须正确覆写equals()方法，相等的两个key实例调用equals()必须返回true；
           ```$xslt
                           //判断对象相等，需重写equals
                               @Override
                               public boolean equals(Object obj) {
                           
                                   if(obj instanceof Person){
                                       Person person = (Person) obj;
                                       return Objects.equals(person.firstname,this.firstname)
                                               && Objects.equals(person.lastname,this.lastname)
                                               && this.age==person.age;
                                   }
                                   return false;
                           
                               }
                           ```
      2. 作为key的对象还必须正确覆写hashCode()方法，且hashCode()方法要严格遵循以下规范：            
             ```
                                @Override
                                 public int hashCode() {
                                      return Objects.hash(firstname,lastname,age);
                                 }
             ```    
             如果两个对象相等，则两个对象的hashCode()必须相等；
             如果两个对象不相等，则两个对象的hashCode()尽量不要相等。
             即对应两个实例a和b：
             如果a和b相等，那么a.equals(b)一定为true，则a.hashCode()必须等于b.hashCode()；
             如果a和b不相等，那么a.equals(b)一定为false，则a.hashCode()和b.hashCode()尽量不要相等。
             
   - 有序map : treeMap
   - 枚举map : enumMap 
          - 枚举类型作为key ,并不需要计算hashcode 存取值。效率高。          
 - 队列: queue
    - 队列只有两个操作(先进先出 FIFO)
      - 把元素添加到队列末尾
      - 从队列头部取出元素     
     	              throw Exception	      返回false或null
     添加元素到队尾	    add(E e)	            boolean offer(E e)
     取队首元素并删除	    E remove()	            E poll()
     取队首元素但不删除	    E element()	            E peek()  
     linkedList 即实现了list接口，也实现了queue接口。 
 - 优先级队列：PriorityQueue           
     ```$xslt
        //测试priorityQueue
        Queue<String> prique = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
      ```        
 - 双端队列deque
   - 既可以添加到队尾，也可以添加到队首；
   - 既可以从队首获取，又可以从队尾获取
       ```$xslt
            Deque实现了一个双端队列（Double Ended Queue），它可以：
            
            将元素添加到队尾或队首：addLast()/offerLast()/addFirst()/offerFirst()；
            从队首／队尾获取元素并删除：removeFirst()/pollFirst()/removeLast()/pollLast()；
            从队首／队尾获取元素但不删除：getFirst()/peekFirst()/getLast()/peekLast()；
            总是调用xxxFirst()/xxxLast()以便与Queue的方法区分开；
            避免把null添加到队列。
       ```     
 - 栈: stack     
   -  栈（Stack）是一种后进先出（LIFO：Last In First Out）的数据结构。  
       push/pop/peek,进行压栈出栈等操作
       
 - 迭代器 : iterator
     ```$xslt
          class Myiterable<T> implements  Iterable<T>{
          
               List<T> list = new ArrayList<>();
               boolean add(T da){
                 return list.add(da);
               };
              @Override
              public Iterator<T> iterator() {
                  return new Myiterator(list.size());
              }
              class Myiterator<T> implements  Iterator<T>{
          
                  private int index;
                  public Myiterator(int size){
                      this.index = size;
                  }
                  @Override
                  public boolean hasNext() {
                      return index>0;
                  }
          
                  @Override
                  public T next() {
                      return (T)Myiterable.this.list.get(--index);
                  }
              }
          }
      ```     
 - 集合工具类 collections
   Collections.shuffle(list); 洗牌算法： 可以打乱list内部元素的位置     
    - 安全集合 Collections.synchronizedList()
     
    
    
    
    
    
    
    
    
          