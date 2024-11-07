import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
class Item {
int weight;
int value;
// Constructor
public Item(int weight, int value) {
this.weight = weight;
this.value = value;
}
}
public class Knapsack {
// Method to calculate the maximum value we can obtain
public static double getMaxValue(Item[] items, int capacity) {
// Sort items by value-to-weight ratio in descending order
Arrays.sort(items, new Comparator<Item>() {
@Override
public int compare(Item o1, Item o2) {
double r1 = (double) o1.value / o1.weight;
double r2 = (double) o2.value / o2.weight;
return Double.compare(r2, r1);
}
});
System.out.println("\nItems sorted by value-to-weight ratio (value, weight):");
for (Item item : items) {
System.out.println("Value: " + item.value + ", Weight: " + item.weight +
", Ratio: " + (double) item.value / item.weight);
}
double totalValue = 0.0; // Maximum value we can carry
int currentWeight = 0; // Current weight in the knapsack
for (Item item : items) {
if (currentWeight + item.weight <= capacity) {
// If the item can fit, take it whole
currentWeight += item.weight;
totalValue += item.value;
System.out.println("Took whole item with value " + item.value +
" and weight " + item.weight);
System.out.println("Current weight: " + currentWeight + "/" + capacity +
", Total value: " + totalValue);
} else {
// Otherwise, take the fractional part of the item
int remainingWeight = capacity- currentWeight;
totalValue += item.value * ((double) remainingWeight / item.weight);
System.out.println("Took " + remainingWeight + " weight from item with value " +
item.value + " and weight " + item.weight);
System.out.println("Current weight: " + capacity + "/" + capacity +
", Total value: " + totalValue);
break; // Knapsack is full
}
}
return totalValue;
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
// Input the number of items
System.out.print("Enter the number of items: ");
int n = sc.nextInt();
// Create an array of items
Item[] items = new Item[n];
// Input item values and weights
for (int i = 0; i < n; i++) {
System.out.print("Enter value and weight for item " + (i + 1) + ": ");
int value = sc.nextInt();
int weight = sc.nextInt();
items[i] = new Item(weight, value);
}
// Input knapsack capacity
System.out.print("Enter the knapsack capacity: ");
int capacity = sc.nextInt();
// Calculate the maximum value
double maxValue = getMaxValue(items, capacity);
System.out.println("\nMaximum value we can obtain = " + maxValue);
sc.close();
}
}
