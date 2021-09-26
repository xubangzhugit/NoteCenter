package javaNote.collection;

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
        //map
        traverse();
        //测试equals
        testequals();

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
}
