/******************************************************************************

Trie Data Structure

*******************************************************************************/

#include <iostream>
#include <map>
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
    cout << word << endl;
    TrieNode current = root;

    for(int i = 0; i < word.length(); i++) {
        auto it = current.children.find(word[i]);
        
        if (it == current.children.end()) { //character is not in the map
            cout << "Char NOT in map" << endl;
            TrieNode node;
            current.children.insert(pair<char,TrieNode>(word[i], node));
            it = current.children.find(word[i]);
            cout << (it == current.children.end() ? "NOT IN" : "IN") << endl;
        }
        else {
            cout << "Char IN map" << endl;
        }
        current = current.children.at(word[i]);
        
    }
    current.endWord = true;
    cout << sizeof(root) << endl;
    return root;
}


int main()
{
    TrieNode root ;
    cout << sizeof(root) << endl;
    root = insertWord("abc",root);

    cout << "HERE" << endl;
    auto it = root.children.find('a');
            cout << (it == root.children.end() ? "NOT IN" : "IN") << endl;
    root = insertWord("acd", root);

    return 0;
}


