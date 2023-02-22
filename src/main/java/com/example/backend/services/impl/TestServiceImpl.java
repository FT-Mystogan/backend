package com.example.backend.services.impl;

import com.example.backend.services.TestService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
public class TestServiceImpl implements TestService {
    int arrayLength = 10000;

    @Override
    public String[] sort(String[] strings,int key) {
        long startTime = Instant.now().toEpochMilli();
        String s = "Time run of ";
        switch (key) {
            case 1:
                s += "Bubble Sort is: ";
                bubbleSort(strings);
                break;
            case 2:
                s += "Selection Sort is: ";
                selectionSort(strings);
                break;
            case 3:
                s += "Insertion Sort is: ";
                insertionSort(strings);
                break;
            case 4:
                s += "Quick Sort is: ";
                quickSort(strings, 0, this.arrayLength - 1);
                break;
            case 5:
                s += "Merge Sort is: ";
                mergeSort(strings, 0, this.arrayLength - 1);
                break;
            default:
                return null;
        }
        System.out.println(s + (Instant.now().toEpochMilli() - startTime) + " milli seconds");
        return strings;
    }

    @Override
    public String[] generate() {
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
        return randomStrings;
    }

    private void bubbleSort(String[] arr) {
        boolean swapped = true;
        int j = 0;
        String tmp;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < arr.length - j; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                    swapped = true;
                }
            }
        }
    }

    private void selectionSort(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            String temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    private void insertionSort(String[] arr) {
        for (int i = 1; i < arr.length; i++) {
            String key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private void quickSort(String[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    private int partition(String[] arr, int left, int right) {
        String pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        String temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;
        return i + 1;
    }

    private void mergeSort(String[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);
            merge(arr, left, middle, right);
        }
    }

    private void merge(String[] arr, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        String[] L = new String[n1];
        String[] R = new String[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[middle + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
