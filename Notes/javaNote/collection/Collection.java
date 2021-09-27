package javaNote.collection;

import java.io.File;
import java.io.IOException;
import java.security.acl.LastOwnerException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Collection {
    static final Logger logger = Logger.getLogger(Collectors.class.getName());
    public static void main(String[] args){
        createList();//创建list
        //list转array(数组
        String[] strings = listChangeArray();
        // array 转list
        List<String> strings1 = Arrays.asList(strings);
        List<Person> per = new ArrayList<>();
        //list 转 map
        per.stream().collect(Collectors.toMap(i->{
            return i.getFirstname();
        },b->{
            return b.getLastname();
        }));
        //map
        traverse();
        //测试equals
        testequals();
        //测试set
        testSet();
        //测试队列
        testQueue();
        //测试双端队列
        testDeque();
        //测试iterator
        testIterator();

        testCollections();
    }

    private static void testCollections() {
        List<String> da = Collections.singletonList("da");//创建单元素list
        List<String> strings = Collections.synchronizedList(new ArrayList<String>());//创建线程安全集合
        strings.add("helele");
        strings.forEach(i->{
            logger.info(i+"线程安全集合");
        });

    }

    private static void testIterator() {
        Myiterable<String> myiter  = new Myiterable<String>();
        myiter.add("a");
        myiter.add("b");
        myiter.add("c");
        myiter.forEach(i->{
            logger.info(i+"自定义迭代器");
        });
    }

    private static void testDeque() {
        //双端队列
        Deque<String> deque = new LinkedList<>();
        deque.offerLast("A"); // A
        deque.offerLast("B"); // A <- B
        deque.offerFirst("C"); // C <- A <- B
        logger.info(deque.pollFirst()); // C, 剩下A <- B
        logger.info(deque.pollLast()); // B, 剩下A
        logger.info(deque.pollFirst()); // A
        logger.info(deque.pollFirst()); // null
    }

    private static void testQueue() {
        Queue<String > queue = new LinkedList<>();
        queue.add("queue1");
        queue.add("queue1");
        queue.add("queue1");//添加队尾元素，添加失败报错
        queue.remove();//获取队首元素并删除。获取失败会报错
        queue.element();// 获取队首元素不删除。获取失败会报错
        queue.offer("a");//添加队尾元素，添加失败返回false
        queue.poll();//获取首元素并删除，获取失败返回null
        queue.peek();//获取首元素不删除，获取失败返回null
        //测试priorityQueue
        Queue<String> prique = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        prique.offer("p");
        prique.offer("r");
        prique.offer("f");
        prique.forEach(i->{
            logger.info(i+"带优先级的queue");
        });
    }

    private static void testSet() {
        Set<String> set = new HashSet<>();
        logger.info(""+set.add("abc")); // true
        logger.info(""+set.add("xyz")); // true
        logger.info(""+set.add("xyqz")); // true
        logger.info(""+set.add("xsayz")); // true
        logger.info(""+set.add("xyz")); // false，添加失败，因为元素已存在
        logger.info(""+set.contains("xyz")); // true，元素存在
        logger.info(""+set.contains("XYZ")); // false，元素不存在
        logger.info(""+set.remove("hello")); // false，删除失败，因为元素不存在
        logger.info(""+set.size()); // 2，一共两个元素
        //hashset 无序set
        set.forEach(i->{
            logger.info(i+"无序set");
        });
        //有序set
        Set<String> setsequ  =  new TreeSet<>();
        setsequ.add("abc1");
        setsequ.add("abc2");
        setsequ.add("abc3");
        setsequ.add("abc4");
        setsequ.forEach(i->{
            logger.info(i+"有序set");
        });
    }

    private static void traverse() {
        Map<String,String> map  = new HashMap<>();
        map.put("firstname","bob");
        map.put("lastname","jeins");
        map.put("card","101");
        Set<String> strings = map.keySet(); //key 集合
        strings.forEach(i->{
            logger.info(map.get(i));

        });
        Set<Map.Entry<String, String>> entries = map.entrySet();
        entries.forEach(i->{
            logger.info(i.getKey()+"<><><><>"+i.getValue());
        });
        Map<String,String> sequMap = new TreeMap<>();
        sequMap.put("key1","value1");
        sequMap.put("key2","value2");
        sequMap.put("key3","value3");
        sequMap.forEach((i,k)->{
            logger.info(i+"<---有序map--->"+k);
        });
    }
    private static void testequals() {
        List<Person> personlist = new ArrayList<>();
        personlist.add(new Person("qq","qq",12));
        personlist.add(new Person("a","a",12));
        personlist.add(new Person("v","v",12));
        logger.info(personlist.contains(new Person("a","a",12))+"<<--->");
    }

    private static String[] listChangeArray() {
        //list转array(数组)
        List<String> larr = new ArrayList<>();
        larr.add("1");
        larr.add("2");
        larr.add("3");
        Object[] objects = larr.toArray(); //会丢失数据类型
        String[] strings = larr.toArray(new String[3]);//不会丢失数据类型
        return strings;
    }

    private static void createList() {
        //创建list
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("bana");
        list.add("apple");
        list.add(null);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            logger.info(iterator.next());
        }
    }
}

class Person{
    private String firstname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String lastname;
    private int age;
    public Person(String fname,String lname,int age){
         this.lastname = lname;
         this.firstname = fname;
         this.age = age;
    }
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

    @Override
    public int hashCode() {
         return Objects.hash(firstname,lastname,age);
    }

    /**
     * 定义枚举
     */
    public enum EY{
       BCD("A",1);
        private String name;

        private int code;

        public String getName() {
            return name;
        }
        public int getCode() {
            return code;
        }
        EY(String na,int a){
            this.name = na;
            this.code = a;
        }
    };

}
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