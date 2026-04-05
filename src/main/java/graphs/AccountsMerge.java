package graphs;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/accounts-merge/description/">Accounts Merge</a>
 * Given a list of accounts where each element accounts[i] is a list of strings,
 * where the first element accounts[i][0] is a name,
 * and the rest of the elements are emails representing emails of the account.
 * Now, we would like to merge these accounts.
 * Two accounts definitely belong to the same person if there is some common email to both accounts.
 * Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
 * A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 * After merging the accounts, return the accounts in the following format:
 * the first element of each account is the name, and the rest of the elements are emails in sorted order.
 * The accounts themselves can be returned in any order.
 */
public class AccountsMerge {
    // Time Complexity: O(N+E) + O(E*4ɑ) + O(N*(ElogE + E)) where N = no. of indices or nodes and E = no. of emails.
    // The first term is for visiting all the emails.
    // The second term is for merging the accounts.
    // And the third term is for sorting the emails and storing them in the answer array.
    // Space Complexity: O(N)+ O(N) +O(2N) ~ O(N) where N = no. of nodes/indices.
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // Lets consider each account/account name as a node
        // By default they are the parent of each other - in DSU Terms
        DisjointSetUnion disjointSetUnion = new DisjointSetUnion(accounts.size());
        HashMap<String, Integer> emailToAccountMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                // If the email is already linked to an account in the map, they do a union between
                // The current account and the one in the map - Then ignore this to be added in the map too
                if (emailToAccountMap.containsKey(email)) {
                    // We say that we can group the current account and the previously email found account in the DSU
                    disjointSetUnion.union(emailToAccountMap.get(email), i);
                } else {
                    emailToAccountMap.put(email, i);
                }
            }
        }
        // Now we have the groups and links in the parents array of DSU
        // Now iterate through the email once again and group them based on the DSU
        List<List<String>> temp = new java.util.ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            temp.add(new ArrayList<>());
        }

        // Iterate from the map and group the accounts
        for (String email : emailToAccountMap.keySet()) {
            int parent = disjointSetUnion.find(emailToAccountMap.get(email));
            temp.get(parent).add(email);
        }
        // Take a final result list
        List<List<String>> result = new ArrayList<>();
        // Now we need to sort the emails and add the account name at the begining of the list
        for (int i = 0; i < temp.size(); i++) {
            List<String> account = temp.get(i);
            // Skip the empty Entries
            if (account.isEmpty()) {
                continue;
            }
            Collections.sort(account);
            // Add the username to the list after sorting
            account.addFirst(accounts.get(i).getFirst());
            // Add the final List into the result List
            result.add(account);
        }
        return result;
    }

    public static void main(String[] args) {
        AccountsMerge accountsMerge = new AccountsMerge();
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(new ArrayList<>(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("Mary", "mary@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("John", "johnnybravo@mail.com")));
        List<List<String>> result = accountsMerge.accountsMerge(accounts);
        System.out.println(result);
    }
}
