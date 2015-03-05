package com.ljd.guava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.BiMap;
import com.google.common.collect.Collections2;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.ljd.guava.model.Foo;

/**
 * Google Guava Collections（以下都简称为 Guava Collections）是 Java Collections
 * Framework 的增强和扩展。<br>
 * Guava Collections 充分利用了 JDK5 的范型和枚举这样的特性，更加严格遵守 Java Collections Framework
 * 定义的接口契约。<br>
 * 可以说 Java Collections Framework
 * 满足了我们大多数情况下使用集合的要求，但是当遇到一些特殊的情况我们的代码会比较冗长，比较容易出错。Guava Collections
 * 可以帮助你的代码更简短精炼，更重要是它增强了代码的可读性。 看看 Guava Collections 为我们做了哪些很酷的事情。<br>
 * 
 * <h1>Immutable Collections: 还在使用 Collections.unmodifiableXXX()？ Immutable Collections 这才是真正的不可修改的集合</h1> 
 * <h1>Multiset: 看看如何把重复的元素放入一个集合</h1> 
 * <h1>Multimaps: 需要在一个 key 对应多个 value 的时候 , 自己写一个实现比较繁琐 - 让 Multimaps 来帮忙</h1> 
 * <h1>BiMap: java.util.Map 只能保证 key 的不重复，BiMap 保证 value 也不重复</h1> 
 * <h1>MapMaker:超级强大的 Map 构造类</h1> 
 * <h1>Ordering class: 大家知道用 Comparator作为比较器来对集合排序，但是对于多关键字排序 Ordering class 可以简化很多的代码</h1> 
 * <h1>其他特性</h1>
 * 参考网址：https://www.ibm.com/developerworks/cn/java/j-lo-googlecollection/
 * @author jclijd
 *
 */
public class GuavaTest {

	/**
	 * 不可修改的collections。Guava Collections 还提供了各种 Immutable 集合的
	 * 实现：ImmutableList，ImmutableMap，ImmutableSortedSet，ImmutableSortedMap。
	 */
	@Test
	public void immutableCollections() {
		Set<String> set = ImmutableSet.of("item1", "item2");
		System.out.println(set);
		//不能进行添加或删除，都抛出异常： throw new UnsupportedOperationException();
//		System.out.println(set.add("item3"));
//		System.out.println(set.remove("item2"));
		
		//可用Builder来将集合以及分散的值合并为一整个不可修改集合
		Builder<String> builder = ImmutableSet.builder();
		ImmutableSet<String> set1 = builder.add("item3").addAll(set).build();
		System.out.println(set1);
	}
	
	/**
	 * 把重复的元素放入集合，普通的 Set 就像这样 :[car, ship, bike]，而 Multiset 会是这样 : [car x 2, ship x 6, bike x 3]。
	 * 事实上，Multiset 并没有实现 java.util.Set 接口，它更像是一个 Bag。
	 * 常用实现 Multiset 接口的类有：<br>
     * <b>HashMultiset: 元素存放于 HashMap</b><br>
     * <b>LinkedHashMultiset: 元素存放于 LinkedHashMap，即元素的排列顺序由第一次放入的顺序决定</b><br>
     * <b>TreeMultiset:元素被排序存放于TreeMap</b><br>
     * <b>EnumMultiset: 元素必须是 enum 类型</b><br>
     * <b>ImmutableMultiset: 不可修改的 Mutiset</b><br>
	 */
	@Test
	public void multiSet() {
		Multiset<String> ms = HashMultiset.create();
		ms.add("item0");
		ms.add("item2");
		ms.add("item0");
		System.out.println(ms);
		
		ms.add("item1", 3);
		System.out.println(ms);
		
		System.out.println(ms.count("item3"));
	}
	
	/**
	 * Muitimap 就是一个 key 对应多个 value 的数据结构。看上去它很像 java.util.Map 的结构，
	 * 但是 Muitimap 不是 Map，没有实现 Map 的接口。设想你对 Map 调了 2 次参数 key 一样的 put 方法，
	 * 结果就是第 2 次的 value 覆盖了第 1 次的 value。但是对 Muitimap 来说这个 key 同时对应了 2 个 value。
	 * 所以 Map 看上去是 : {k1=v1, k2=v2,...}，而 Muitimap 是 :{k1=[v1, v2, v3], k2=[v7, v8],....}。
	 * 
	 * Muitimap 接口的主要实现类有：<br>
     *<b>HashMultimap: key 放在 HashMap，而 value 放在 HashSet，即一个 key 对应的 value 不可重复</b><br>
     *<b>ArrayListMultimap: key 放在 HashMap，而 value 放在 ArrayList，即一个 key 对应的 value 有顺序可重复</b><br>
     *<b>LinkedHashMultimap:key放在 LinkedHashMap，而 value放在 LinkedHashSet，即一个 key对应的 value有顺序不可重复</b><br>
     *<b>TreeMultimap: key 放在 TreeMap，而 value 放在 TreeSet，即一个 key 对应的 value 有排列顺序</b><br>
     *<b>ImmutableMultimap: 不可修改的 Multimap</b><br>
	 */
	@Test
	public void multiMap() {
		Multimap<String, String> map = HashMultimap.create();
		map.put("item0", "v1");
		map.put("item0", "v2");
		map.put("item1", "v0");
		System.out.println(map);
	}
	
	@Test
	public void bimap() {
		BiMap<String, String> map = HashBiMap.create();
		map.put("item0", "v1");
//		map.put("item1", "v1");		//不能有value相同的数据
		map.put("item0", "v0");
		
		System.out.println(map);
		
	}
	
	@Test
	public void concurrentMap() {
		
		Cache<String, String> map = CacheBuilder.newBuilder() 
	    .concurrencyLevel(1) 		//同一时刻可被多少个线程使用
	    .softValues()
	    .recordStats()		//记录访问信息，可用map.stats()查看
	     .build(
	    	//命中失败时会通过load方法将查询键值加入缓存。
	    	new CacheLoader<String, String>() {
	    		public String load(String key) throws Exception {
	    			return key + "kick your dick";
	    		}
	    	});
		
		map.put("item0", "v0");
//		map.put("item1", "v1");
		System.out.println(map.getIfPresent("item0"));
		try {
			System.out.println(map.get("item1", new Callable<String>() {

				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub
					return "what the fuck";
				}
			}));
			System.out.println(map.get("item1", new Callable<String>() {

				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub
					return "what the hell";
				}
			}));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(map.getIfPresent("item1"));
		System.out.println(map.stats());
		
	}
	
	@Test
	public void compare() {
		List<Foo> list = new ArrayList<Foo>();
		list.add(new Foo(8, 23, 90));
		list.add(new Foo(2, 42, 87));
		list.add(new Foo(8, 3, 0));
		System.out.println(list);
		Collections.sort(list, new Comparator<Foo>() {

			@Override
			public int compare(Foo f1, Foo f2) {
				return ComparisonChain.start()
						.compare(f1.a, f2.a)
						.compare(f1.b, f2.b)
						.compare(f1.c, f2.c).result();
			}
		});
		System.out.println(list);
		
		Collections.sort(list, new Comparator<Foo>() {

			@Override
			public int compare(Foo f1, Foo f2) {
				return ComparisonChain.start()
						.compare(f1.a, f2.a, new Foo.CompareA())
						.compare(f1.b, f2.b, new Foo.CompareA())
						.compare(f1.c, f2.c, new Foo.CompareA()).result();
			}
		});
		System.out.println(list);
	}
	
	@Test
	public void collection2() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(4);
		list.add(10);
		list.add(9);
		System.out.println(list);
		
		Collection<Integer> list1 = Collections2.filter(list, new Predicate<Integer>() {

			@Override
			public boolean apply(Integer input) {
				// TODO Auto-generated method stub
				return input > 8;
			}
		});
		System.out.println(list1);
		//不能加入小于8的值
//		list1.add(5);
		
	}
}
