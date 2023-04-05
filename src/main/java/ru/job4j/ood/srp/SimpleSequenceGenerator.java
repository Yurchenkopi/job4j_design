package ru.job4j.ood.srp;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimpleSequenceGenerator implements SequenceGenerator<Integer> {

    private final NumberGenerator<Integer> numberGenerator;

    public SimpleSequenceGenerator(NumberGenerator<Integer> numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Override
    public List<Integer> generate(int size) {
        return IntStream.range(0, size)
                .map(i -> numberGenerator.generate()).boxed()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(new SimpleSequenceGenerator(new NumberGenerator<Integer>() {
            @Override
            public Integer generate() {
                Random random = new Random();
                return random.nextInt();
            }
        }).generate(10));
    }
}