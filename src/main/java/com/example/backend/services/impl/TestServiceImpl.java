package com.example.backend.services.impl;

import com.example.backend.services.TestService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TestServiceImpl implements TestService {
    private String[] strings;
    int arrayLength = 1000;

    @Override
    public String[] solve(int key) {
        switch (key) {
            case 0:
                return generate();
            case 1:
                return bubbleSort(this.strings);
            case 2:
                return selectionSort(this.strings);
            case 3:
                return insertionSort(this.strings);
            case 4:
                return quickSort(this.strings, 0, this.arrayLength - 1);
            case 5:
                return mergeSort(this.strings, 0, this.arrayLength - 1);
        }
        return null;
    }

    private String[] generate() {
        Random random = new Random();
        String[] randomStrings = new String[arrayLength];

        for (int i = 0; i < arrayLength; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            int stringLength = random.nextInt(5) + 1;

            for (int j = 0; j < stringLength; j++) {
                char c = (char) (random.nextInt(26) + 'a');
                if (random.nextBoolean()) {
                    c = Character.toUpperCase(c);
                }
                stringBuilder.append(c);
            }

            randomStrings[i] = stringBuilder.toString();
        }
        this.strings = randomStrings;
        return strings;
    }

    private String[] bubbleSort(String[] strings) {
        boolean swapped = true;
        int j = 0;
        String tmp;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < strings.length - j; i++) {
                if (strings[i].compareTo(strings[i + 1]) > 0) {
                    tmp = strings[i];
                    strings[i] = strings[i + 1];
                    strings[i + 1] = tmp;
                    swapped = true;
                }
            }
        }
        return strings;
    }

    private String[] selectionSort(String[] strings) {
        for (int i = 0; i < strings.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < strings.length; j++) {
                if (strings[j].compareTo(strings[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            String temp = strings[minIndex];
            strings[minIndex] = strings[i];
            strings[i] = temp;
        }
        return strings;
    }

    private String[] insertionSort(String[] strings) {
        for (int i = 1; i < strings.length; i++) {
            String key = strings[i];
            int j = i - 1;
            while (j >= 0 && strings[j].compareTo(key) > 0) {
                strings[j + 1] = strings[j];
                j--;
            }
            strings[j + 1] = key;
        }
        return strings;
    }

    private String[] quickSort(String[] strings, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(strings, left, right);
            quickSort(strings, left, pivotIndex - 1);
            quickSort(strings, pivotIndex + 1, right);
        }
        return strings;
    }

    private int partition(String[] strings, int left, int right) {
        String pivot = strings[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (strings[j].compareTo(pivot) <= 0) {
                i++;
                String temp = strings[i];
                strings[i] = strings[j];
                strings[j] = temp;
            }
        }
        String temp = strings[i + 1];
        strings[i + 1] = strings[right];
        strings[right] = temp;
        return i + 1;
    }
    private String[] mergeSort(String[] strings, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(strings, left, middle);
            mergeSort(strings, middle + 1, right);
            merge(strings, left, middle, right);
        }
        return strings;
    }
    private void merge(String[] strings, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        String[] L = new String[n1];
        String[] R = new String[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = strings[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = strings[middle + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                strings[k] = L[i];
                i++;
            } else {
                strings[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            strings[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            strings[k] = R[j];
            j++;
            k++;
        }
    }
}
