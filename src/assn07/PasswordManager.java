package assn07;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class PasswordManager<K, V> implements Map<K, V> {
    private static final String MASTER_PASSWORD = "mariaisawesome"; // You can change this
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
    }

    /**
     * Helper to compute array index from key hash code.
     */
    private int getIndexForKey(K key) {
        int hash = key.hashCode();
        // Avoid overflow when taking absolute value of Integer.MIN_VALUE
        if (hash == Integer.MIN_VALUE) {
            hash = 0;
        }
        int nonNegative = Math.abs(hash);
        return nonNegative % _passwords.length;
    }

    // TODO: put
    @Override
    public void put(K key, V value) {
        if (key == null) {
            return; // don't store null keys
        }

        int index = getIndexForKey(key);
        Account head = _passwords[index];

        // If bucket empty, just insert
        if (head == null) {
            _passwords[index] = new Account(key, value);
            return;
        }

        // Traverse chain: update existing key if found, otherwise append at tail
        Account current = head;
        Account prev = null;
        while (current != null) {
            if (current.getWebsite().equals(key)) {
                // Key already exists, update password
                current.setPassword(value);
                return;
            }
            prev = current;
            current = current.getNext();
        }

        // Key not found, append new Account at the tail (chaining at tail)
        prev.setNext(new Account(key, value));
    }

    // TODO: get
    @Override
    @SuppressWarnings("unchecked")
    public V get(K key) {
        if (key == null) {
            return null;
        }

        int index = getIndexForKey(key);
        Account current = _passwords[index];

        while (current != null) {
            if (current.getWebsite().equals(key)) {
                return (V) current.getPassword();
            }
            current = current.getNext();
        }

        // Not found
        return null;
    }

    // TODO: size
    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < _passwords.length; i++) {
            Account current = _passwords[i];
            while (current != null) {
                count++;
                current = current.getNext();
            }
        }
        return count;
    }

    // TODO: keySet
    @Override
    @SuppressWarnings("unchecked")
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();

        for (int i = 0; i < _passwords.length; i++) {
            Account current = _passwords[i];
            while (current != null) {
                keys.add((K) current.getWebsite());
                current = current.getNext();
            }
        }

        return keys;
    }

    // TODO: remove
    @Override
    @SuppressWarnings("unchecked")
    public V remove(K key) {
        if (key == null) {
            return null;
        }

        int index = getIndexForKey(key);
        Account current = _passwords[index];
        Account prev = null;

        while (current != null) {
            if (current.getWebsite().equals(key)) {
                // Remove this node from chain
                V oldValue = (V) current.getPassword();

                if (prev == null) {
                    // Removing head of chain
                    _passwords[index] = current.getNext();
                } else {
                    prev.setNext(current.getNext());
                }

                return oldValue;
            }

            prev = current;
            current = current.getNext();
        }

        // Not found
        return null;
    }

    // TODO: checkDuplicate
    @Override
    @SuppressWarnings("unchecked")
    public List<K> checkDuplicates(V value) {
        List<K> duplicates = new ArrayList<>();

        for (int i = 0; i < _passwords.length; i++) {
            Account current = _passwords[i];
            while (current != null) {
                if (current.getPassword().equals(value)) {
                    duplicates.add((K) current.getWebsite());
                }
                current = current.getNext();
            }
        }

        return duplicates;
    }

    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return enteredPassword.equals(MASTER_PASSWORD);
    }

    @Override
    public int[] collisionStats() {
        int totalAccounts = 0;
        int bucketsUsed = 0;
        int bucketsWithCollisions = 0;
        int maxChainLength = 0;

        for (int i = 0; i < _passwords.length; i++) {
            Account current = _passwords[i];

            if (current != null) {
                bucketsUsed++;
                int chainLength = 0;

                while (current != null) {
                    chainLength++;
                    totalAccounts++;
                    current = current.getNext();
                }

                if (chainLength > 1) {
                    bucketsWithCollisions++;
                }

                if (chainLength > maxChainLength) {
                    maxChainLength = chainLength;
                }
            }
        }

        // [0] = total number of accounts
        // [1] = number of buckets in use
        // [2] = number of buckets with collisions (chains > 1)
        // [3] = maximum chain length
        return new int[]{totalAccounts, bucketsUsed, bucketsWithCollisions, maxChainLength};
    }

    @Override
    public String generatesafeRandomPassword(int length) {
        int leftLimit = 48;  // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        // TODO: Ensure the minimum length is 4
        if (targetStringLength < 4) {
            targetStringLength = 4;
        }

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                // Keep only 0-9, A-Z, a-z
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
