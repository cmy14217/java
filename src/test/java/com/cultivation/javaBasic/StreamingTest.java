package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.AnimeCharacter;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.ValueHolder;
import com.cultivation.javaBasicExtended.simpleMvc3.Controller;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.*;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

class StreamingTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_collection_into_stream() {
        List<String> words = Arrays.asList("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = words.stream();
        // --end-->
        {
            assertIterableEquals(
                words,
                wordStream.collect(Collectors.toList())
            );
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_array_into_stream() {
        String[] words = {"a", "quick", "brown", "fox", "jumps", "over"};

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = Arrays.stream(words);
        // --end-->
        {
            assertArrayEquals(
                words,
                wordStream.toArray(String[]::new)
            );
        }
    }

    @Test
    void int_array_to_stream() {
        Float[] intArray = {1.0f,2.0f,3.0f};
        Stream<Float> wordStream = Arrays.stream(intArray);
        assertArrayEquals(intArray, wordStream.toArray());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_empty_stream() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> emptyWordStream = Stream.empty();
        // --end-->
        {
            assertEquals(0, emptyWordStream.count());
        }
    }

    @Test
    void should_stream_address_not_same() {
        Stream<Integer> originStream = Stream.of(1, 2, 3);
        Stream filterStream = originStream.filter(i -> i>1);
        assertFalse(originStream == filterStream);
    }

    @Test
    void should_filter_not_close_stream() {
        Stream<Integer> initialStream = Stream.of(1, 2, 3);
        final boolean[] isClosed = {false};
        initialStream.onClose(() -> isClosed[0] = true);
        //Stream filterStream = initialStream.filter(i -> i>1);
        initialStream.toArray();
        assertFalse(isClosed[0]);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_with_same_items() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> infiniteEchos = Stream.generate(() -> "Echo");
        // --end-->
        {
            assertEquals("Echo", infiniteEchos.skip(10000).findFirst().get());
        }
    }

    @Test
    void should_be_zero_when_skip_10000() {
        final int count[] = new int[]{0};
        Stream<Integer> infiniteIntegers = Stream.iterate(0, i -> count[0]++);
        infiniteIntegers.skip(10000).findFirst().get();
        assertEquals(10000, count[0]);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_of_sequence() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> i+1);
        // --end-->
        {
            assertEquals(10000, infiniteSequence.skip(10000).findFirst().get().intValue());
        }
    }

    @SuppressWarnings({"TryFinallyCanBeTryWithResources", "unused", "ConstantConditions"})
    @Test
    void should_be_able_to_filter_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.filter(n -> n.length() > 4);
        // --end-->
        {
            assertArrayEquals(new String[]{"quick", "brown", "jumps"}, filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.map(n -> n.toUpperCase());
        // --end-->
        {
            assertArrayEquals(
                new String[]{"A", "QUICK", "BROWN", "FOX", "JUMPS", "OVER"},
                filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_item_to_a_new_type() {
        Stream<String> nameStream = Stream.of("Naruto", "Kisuke", "Tomoya");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<AnimeCharacter> characters = nameStream.map(AnimeCharacter::new);
        // --end-->
        {
            AnimeCharacter[] actual = characters.toArray(AnimeCharacter[]::new);
            assertArrayEquals(
                new AnimeCharacter[] {
                    new AnimeCharacter("Naruto"),
                    new AnimeCharacter("Kisuke"),
                    new AnimeCharacter("Tomoya")
                },
                actual);
        }
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_flatten_stream_of_streams() {
        Stream<Stream<Character>> streamArrayCharacters = Stream.of("Abc", "Def", "Hij").map(StreamingTest::letters);
        Stream<Character> streamCharacters = streamArrayCharacters.flatMap(i -> i);
        assertArrayEquals(new Character[]{'A', 'b', 'c', 'D', 'e', 'f', 'H', 'i', 'j'}, streamCharacters.toArray(Character[]::new));

        //assertEquals(Object.class, streamCharacters.toArray().getClass());
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_create_sequence_of_specified_size() {
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> i + 1);

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> finiteStream = infiniteSequence.limit(10);
        // --end-->
        {
            assertArrayEquals(
                new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                finiteStream.toArray(Integer[]::new)
            );
        }
    }

    @Test
    void should_limit_run_times() {
        final int[] runTimesCount = new int[]{0};
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> {
            runTimesCount[0]++;
            return i + 1;
        });
        int limitNumber = 10;
        infiniteSequence.limit(limitNumber).toArray(Integer[]::new);
        assertEquals(limitNumber -1, runTimesCount[0]);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_concat_streams() {
        Stream<Character> helloStream = Stream.of('H', 'e', 'l', 'l', 'o');
        Stream<Character> worldStream = Stream.of('W', 'o', 'r', 'l', 'd');

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> concatStream = Stream.concat(helloStream, worldStream);
        // --end-->
        {
            assertArrayEquals(
                letters("HelloWorld").toArray(Character[]::new),
                concatStream.toArray(Character[]::new)
            );
        }
    }

    @Test
    void should_sorted_stream_concat_get_sorted_stream() {
        Stream<Character> abStream = Stream.of('b', 'c');
        Stream<Character> cdStream = Stream.of('d', 'a', 'e');
        Stream<Character> sortedABStream = abStream.sorted();
        Stream<Character> sortedCDStream = cdStream.sorted();
        Stream<Character> concatStream = Stream.concat(sortedABStream, sortedCDStream);
        assertArrayEquals(new Character[]{'b','c','a','d', 'e'}, concatStream.toArray(Character[]::new));
    }

    @Test
    void should_get_sorted_stream_after_sorted() {
        Stream<Character> abcStream = Stream.of('b','a','c');
        assertArrayEquals(new Character[]{'a', 'b', 'c'}, abcStream.sorted().toArray(Character[]::new));
    }

    @SuppressWarnings({"SpellCheckingInspection", "unused", "ConstantConditions"})
    @Test
    void should_get_unique_items() {
        Stream<Character> characterStream = letters("aquickbrownfox");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> distinct = characterStream.distinct();
        // --end-->
        {
            Character[] characters = distinct.sorted().toArray(Character[]::new);

            assertArrayEquals(
                new Character[] {'a', 'b', 'c', 'f', 'i', 'k', 'n', 'o', 'q', 'r', 'u', 'w', 'x'},
                characters
            );
        }
    }

    @Test
    void should_hook_stream_generation() {
        ValueHolder<Integer> holder = new ValueHolder<>();
        holder.setValue(0);

        Stream<Integer> hookStream = Stream
            .iterate(0, i -> i + 1)
            .limit(20)
            .filter(v -> v % 2 == 0)
            .peek(v -> holder.setValue(holder.getValue() + v));

        hookStream.forEach(i -> {});

        // TODO: please modify the following code to pass the test
        // <--start
        final int expected = 90;
        // --end-->

        assertEquals(expected, holder.getValue().intValue());
    }

    @Test
    void should_hook_affect_stream_orNot() {
        ValueHolder<Integer> holder = new ValueHolder<>();
        holder.setValue(1);
        Stream<ValueHolder<Integer>> hookStream = Stream.of(holder).peek(v -> {
            v.setValue(2);
        });
        hookStream.forEach(i -> {});
        assertEquals(new Integer(2), holder.getValue());

    }

    @SuppressWarnings({"ConstantConditions", "unchecked", "OptionalAssignedToNull"})
    @Test
    void should_throws_if_get_value_of_empty_optional() {
        // TODO: please create an empty optional and specify the concrete exception type.
        // <--start
        Optional<String> empty = Optional.empty();
        Class<? extends NoSuchElementException> errorType = new NoSuchElementException().getClass();
        // --end-->

        assertThrows(errorType, empty::get);
    }

    @Test
    void should_provide_a_default_value_using_or_else() {
        Optional<String> empty = Optional.empty();
        Optional<String> nonEmpty = Optional.of("great");

        String emptyResult = getValue(empty, "default value");
        String nonEmptyResult = getValue(nonEmpty, "default value");

        assertEquals("default value", emptyResult);
        assertEquals("great", nonEmptyResult);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_throw_for_empty_optional() {
        Optional<String> empty = Optional.empty();

        // TODO: In the `Runnable` object. Please throw IllegalStateException when `empty` is not present.
        // <--start
        Runnable emptyRunnable = () -> empty.orElseThrow(IllegalStateException::new);
        // --end-->

        assertThrows(IllegalStateException.class, emptyRunnable::run);
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "ConstantConditions"})
    @Test
    void should_process_value_if_present() {
        Optional<String> optional = Optional.of("word");
        List<String> result = new ArrayList<>();

        // TODO: please add the upper-cased value to result if `optional` is present in `Consumer<Optional<String>>`
        // TODO: implementation.
        // <--start
        Consumer<Optional<String>> optionalConsumer = s -> {
            if(s.isPresent()){
                result.add(s.get().toUpperCase());
            }
        };
        // --end-->

        optionalConsumer.accept(optional);

        assertEquals("WORD", result.get(0));
    }

    @SuppressWarnings({"ConstantConditions", "MismatchedQueryAndUpdateOfCollection"})
    @Test
    void should_map_value_if_present() {
        Optional<String> optional = Optional.of("word");
        Optional<String> empty = Optional.empty();

        List<String> result = new ArrayList<>();

        // TODO: The `Function<Optional<String>, Optional<Boolean>>` will map `Optional<String>` to `Optional<Boolean>`
        // TODO: please add the upper-cased value to `result` list if optional is present. Then return the boolean
        // TODO: mapping result of `result.add`.
        // <--start
        Function<Optional<String>, Optional<Boolean>> mapping = optionalS ->
                optionalS.map(
                        (t) -> result.add(t.toUpperCase())
                );
        // --end-->

        Optional<Boolean> mappingResult = mapping.apply(optional);
        Optional<Boolean> emptyMappingResult = mapping.apply(empty);

        assertTrue(mappingResult.orElseThrow(IllegalStateException::new));
        assertEquals("WORD", result.get(0));
        assertFalse(emptyMappingResult.isPresent());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_flat_map_optional_value_do_chain_method() {
        Stream<YieldOptional> emptyStream = Stream.of(1, 2, 3)
            .filter(i -> i > 4)
            .map(i -> new YieldOptional());
        Stream<YieldOptional> nonEmptyStream = Stream.of(1, 2, 3)
            .filter(i -> i > 1)
            .map(i -> new YieldOptional());

        // TODO: The `findFirstAndGet` interface will find first item in stream. If it can be found, map it with
        // TODO: `YieldOptional.get` method. Otherwise, returns empty Optional.
        // <--start
        Function<Stream<YieldOptional>, Optional<String>> findFirstAndGet = yieldOptionalStream ->
            yieldOptionalStream.findFirst().flatMap(YieldOptional::get)
        ;
        // --end-->

        Optional<String> emptyStreamResult = findFirstAndGet.apply(emptyStream);
        Optional<String> nonEmptyStreamResult = findFirstAndGet.apply(nonEmptyStream);

        assertFalse(emptyStreamResult.isPresent());
        assertTrue(nonEmptyStreamResult.isPresent());
        assertEquals("Hello", nonEmptyStreamResult.get());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_result() {
        final int[] executeCount = new int[]{0,0,0};
        Stream<String> stringStream = Stream.of("a","b","c","d","e","f");
        ArrayList<String> list = stringStream.parallel().collect(
                () -> {
                    executeCount[0]++;
                    return new ArrayList<>();},
                (stringList, string) -> {
                    stringList.add(string);
                    executeCount[1]++;},
                (list1, list2) -> {
                    list1.addAll(list2);
                    executeCount[2]++;}
        );

        assertEquals(ArrayList.class, list.getClass());
        assertIterableEquals(Arrays.asList("a","b","c","d","e","f"),list);
        assertTrue(executeCount[0] == executeCount[1] - 1);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_map() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: please implement toMap collector using `stream.collect`. You cannot use existing `toMap` collector.
        // <--start
        HashMap<String, Integer> map = stream.collect(
                HashMap::new,
                (theMap, element) -> theMap.put(element.getKey(), element.getValue()),
                HashMap::putAll
        );
        // --end-->

        assertEquals(2, map.size());
        assertTrue(map.containsKey("Harry"));
        assertEquals(2033, map.get("Harry").intValue());
        assertTrue(map.containsKey("Bob"));
        assertEquals(2014, map.get("Bob").intValue());
    }


    @Test
    void should_store_0_9_use_collector() {
        Stream<Integer> numberStream = Stream.iterate(0, i -> i+1).limit(10);
        HashMap<Integer, List<Integer>> numberMap = numberStream.collect(Collector.of(
                HashMap::new,
                (theMap, element) -> {
                    int key = element.intValue() % 3;
                    if (theMap.get(key) != null){
                        List<Integer> value = theMap.get(key);
                        value.add(element.intValue());
                        theMap.put(key, value);
                    }else{
                        theMap.put(key, new ArrayList<>(Arrays.asList(element.intValue())));
                    }
                },
                (map1, map2) -> {
                    map2.forEach((key, value) -> {
                        if(map1.containsKey(key)){
                            map1.get(key).addAll(value);
                        } else {
                            map1.put(key, value);
                        }
                    });
                    return map1;
                }));
        assertEquals(Arrays.asList(1,4,7), numberMap.get(1));
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You cannot use existing `groupingBy` collector.
        // <--start
        HashMap<String, List<Integer>> map = stream.collect(
                HashMap::new,
                (theMap, element) -> {
                    if(theMap.get(element.getKey()) == null){
                        theMap.put(element.getKey(), new ArrayList<>(Arrays.asList(element.getValue())));
                    }else{
                        List<Integer> values = theMap.get(element.getKey());
                        values.add(element.getValue());
                        theMap.put(element.getKey(), values);
                    }
                },
                (map1, map2) -> {
                    map2.forEach((key, value) -> {
                        if(map1.containsKey(key)){
                            map1.get(key).addAll(value);
                        } else {
                            map1.put(key, value);
                        }
                    });
                });

        // --end-->

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_collect_to_group_continued() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. This time please use `Collectors.groupingBy`
        // <--start
        Map<String, List<Integer>> map = stream.collect(
                Collectors.groupingBy(KeyValuePair::getKey,
                        Collector.of(
                                ArrayList::new,
                                (list, downStreamElement) -> list.add(downStreamElement.getValue()),
                                (list1, list2) -> {
                                    list1.addAll(list2);
                                    return list1;
                                })));
        // --end-->

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_calculate_number_in_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start
        Map<String, Long> map = stream.collect(
                Collectors.groupingBy(KeyValuePair::getKey,
                        Collector.of(
                                () -> new long[1],
                                (sum, downStreamElement) ->  {
                                    sum[0]++;
                                },
                                (sum1, sum2) -> {
                                    sum1[0] += sum2[0];
                                    return sum1;
                                },
                                finalSum -> finalSum[0]
                        )
                )
        );
        // --end-->

        assertEquals(2, map.size());
        assertEquals(2, map.get("Harry").longValue());
        assertEquals(1, map.get("Bob").longValue());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_calculate_sum_of_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start
        Map<String, Integer> map = stream.collect(
                Collectors.groupingBy(
                        KeyValuePair::getKey,
                        Collector.of(
                                () -> new int[1],
                                (sum, element) -> sum[0] += element.getValue(),
                                (sum1, sum2) -> {
                                    sum1[0] += sum2[0];
                                    return sum1;
                                },
                                finalResult -> finalResult[0]

                        )
                )
        );
        // --end-->

        assertEquals(2, map.size());
        assertEquals(4035, map.get("Harry").intValue());
        assertEquals(2014, map.get("Bob").intValue());
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "OptionalAssignedToNull"})
    @Test
    void should_calculate_sum_using_reduce() {
        List<Integer> numbers = new ArrayList<>();
        Stream
            .iterate(1, i -> i + 1)
            .limit(100)
            .forEach(numbers::add);

        // TODO: please modify the following code to pass the test
        // <--start
        Optional<Integer> reduced = numbers.stream().reduce((a, b) -> a+b);
        // --end-->

        //noinspection ConstantConditions
        assertEquals(5050, reduced.get().intValue());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_calculate_total_character_lengths() {
        List<String> words = Arrays.asList("The", "future", "is", "ours");

        // TODO: please calculate the total number of characters using `reduce`.
        // <--start
        Integer total = words.stream().reduce(
                0,
                (totalLength, i) -> totalLength + i.length(),
                (a, b) -> a+b);
        // --end-->

        assertEquals(15, total.intValue());
    }

    @SuppressWarnings({"SameParameterValue", "OptionalUsedAsFieldOrParameterType"})
    private static <T> T getValue(Optional<T> optional, T defaultValue) {
        // TODO: please implement the following method to pass the test
        // <--start
        return optional.orElse(defaultValue);
        // --end-->
    }

    private static Stream<Character> letters(String text) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < text.length(); ++i) {
            characters.add(text.charAt(i));
        }
        return characters.stream();
    }
}

class YieldOptional {
    Optional<String> get() {
        return Optional.of("Hello");
    }
}

/*
 * - Can you use `collect` method to implement `joining(String delimiter)` method?
 * - What can you do using primitive types with streams?
 */