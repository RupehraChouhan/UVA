/******************************************************************************
Trie Data Structure
*******************************************************************************/

#include <iostream>
#include <map>
#include <set>
using namespace std;

struct TrieNode {
    map<char, TrieNode> children;
    bool endWord;
    
    TrieNode() {
        map<char,TrieNode> children;
        endWord = false;
    }
};

TrieNode insertWord(string word, TrieNode root) {
    cout << "inserting: " << word << endl;
    TrieNode  * current = &root;

    for(int i = 0; i < word.length(); i++) {
        
        if (current->children.find(word[i]) == current->children.end()) { //character is not in the map
            cout << "Char '" << word[i] << "' NOT in map" << endl;
            TrieNode node;
            current->children.insert(pair<char,TrieNode>(word[i], node));
            
            cout << (current->children.find(word[i]) == current->children.end() ? "NOT IN" : "IN") << endl;
        }
        else {
            cout << "Char '" << word[i] << "' IN map" << endl;
        }
        current = &current->children.at(word[i]);
        
        // for (auto it = current.children.begin(); it != current.children.end(); ++it)
        //     cout << it->first << endl;
    }
    current->endWord = true;
    return root;
}


set<string> generateSuffixes(string word) {
    set<string> uniqueSuffixes;
    int lenOfSuffix = 1;
    
    for(int i = word.length()-1; i>0; i--) {
        string suffix = word.substr(i,lenOfSuffix++);
        uniqueSuffixes.insert(suffix);
    }
    return uniqueSuffixes;
}


int main(){
    
    TrieNode root;
    
    set<string> allSuffixes = generateSuffixes("competitives");
    for (auto it=allSuffixes.begin(); it!=allSuffixes.end(); ++it) {
        cout << *it << endl;
        root = insertWord(*it, root);
    }
    
    
    // root = insertWord("abc",root);
    // root = insertWord("acd", root);
    // root = insertWord("bac", root);
    // root = insertWord("babd", root);
    
    // set<string> allSuffixes = generateSuffixes("rupehra");
    // for (auto it=allSuffixes.begin(); it!=allSuffixes.end(); ++it) {
    //     insertWord(*it, root);
    // }

    return 0;
}


/*
NOTES:
After adding 'abc' when I tried adding 'acd', it kept on saying that 'a' doesn't exist
even though I had added it earlier. Turns out, I wasn't referencing my current trinode 
to the root. I made current 'a pointer' to the root and that fixed it. (Remember this
next time.)


*/
