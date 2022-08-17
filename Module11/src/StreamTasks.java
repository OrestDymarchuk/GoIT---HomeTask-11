import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class StreamTasks {
	
	// Task #1
	private static String namesByOddIndexes(List<String> names) {
		
		for (int i = 0; i < names.size(); i++) {
			names.set(i, (i+1) + ". " + names.get(i));
		}
		
		return IntStream.range(0, names.size())
				.filter(i -> i % 2 == 0)
				.mapToObj(names::get)
				.collect(Collectors.joining(", "));
	}

	// Task #2
	private static List<String> sortByAlphabeticallyReverse(List<String> names) {
		return names.stream()
				.map(name -> name.toUpperCase())
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
	}
	
	// Task #3
	private static String sortedStringOfNumber(String[] numbers) {
		return Arrays
				.stream(numbers)
				.map(s -> s.replaceAll("\\D", ""))
				.map(s -> s.split(""))
				.flatMap(Arrays::stream)
				.sorted()
				.collect(Collectors.joining(", "));
	}
	
	// Task #4
	private static Stream<Long> infiniteStream() {
		long amp = 25214903917L;
		long inc = 11;
		long mod = (long) Math.pow(2, 48);
		long seed = 0L;

		return Stream.iterate(seed, x -> (amp * (x + inc) % mod));
	}

	// Task #5
	public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
		List<T> firstList = first.collect(Collectors.toList());
		List<T> secondList = second.collect(Collectors.toList());

		int size = Math.min(firstList.size(), secondList.size());

		List<T> result = new ArrayList<>();

		IntStream.range(0, size).forEach((element) -> {
			result.add(firstList.get(element));
			result.add(secondList.get(element));
		});

		return result.stream();
	}

	public static void main(String[] args) {
		// Tasks number #1 and #2
		List<String> names = Arrays.asList("Beta", "Tau", "Gamma", "Omega", "Alpha");
		
		System.out.println("A list of names in odd positions of the list -> \t" + namesByOddIndexes(names));
		System.out.println();
		System.out.println("Sorted list of names in reverse alphabetical order "
				+ "and in upper case -> \t" + sortByAlphabeticallyReverse(names));
		System.out.println();
		
		
		// Task number #3
		String[] numbers = new String[] { "1, 2, 0", "4, 5" };
		
		System.out.println("Sorted string of numbers -> \t" + sortedStringOfNumber(numbers));
		System.out.println();
		
		
		// Task number #4
		Stream<Long> randNums = infiniteStream();
		
		// Uncomment to run the program
		
		//randNums.forEach(System.out::println);
		
		
		// Task number #5
		Stream<String> first = Stream.of("1", "2", "3");
		Stream<String> second = Stream.of("4", "5", "6", "7");		
		List<String> taskFiveResult = zip(first, second).collect(Collectors.toList());

		System.out.println("The result of task #5 is -> \t" + taskFiveResult);

	}
}