# SecLists & ffuf (Fuzz Faster U Fool)

SecLists is a collection of multiple types of lists used during security assessments. List types include usernames, passwords, URLs, sensitive data grep strings, fuzzing payloads, and many more. With the goal to enable security testers to pull the repo onto a new testing box and have access to every type of list that may be needed.

Fuzzing tool, often used for web application pen testing. Giving it a URL with word FUZZ will brute force all the combinations. Interestingly, it was written in GO.

Often combined with word lists. These, can be open source or you can create your own. A common tool used with ffuf is SecLists.
SecLists and ffuf are automatically included in the following Linux distributions:
- BlackArch
- Pentoo
- Kali
- Parrot
- You can also search [Repology](https://repology.org/project/ffuf/versions) for other distributions

## Installation

Otherwise you can install them with the following commands (Linux):

- SecLists: `sudo apt install seclists`
- ffuf: `sudo apt install ffuf`

### ffuf

- very popular in the bug-bounty community
- ffuf uses wordlists and an endpoint (typically URLs)
- goes through all combinations of the inputs in the wordlist
- outputs JSON/CSV data which can then be used in other tools for further testing/analysis
    - difficulty with complex brute forcing (i.e. parameters), but possible
- faster than burp and free

`ffuf -h` or `ffuf man` will give further information about optional flags

### SecLists

To find the SecLists installed you can run:

`locate SecLists`

## ffuf Command Breakdown

`ffuf -w <PATH/wordlist.txt> -u <TARGET/URL>/FUZZ -o <output.txt>`

- can add a replay for valid URLS and put them into burp or other tools by adding the `-replay-proxy` flag and piping it into your desired tool
- can add a flag to filter through any status(es) you are interested in by making them comma separated
    - i.e. `-mc 200,301`
- can add more extensions to your wordlist to discover hidden files; these added extensions are added to the words in the wordlist
    - i.e. `ffuf -w <PATH/wordlist.txt> -u <TARGET/URL>/FUZZ -e .php,.zip,.txt`
        - `-e` adds the following arguments as extensions
        - for example, if your wordlist contains 'admin' and you add the .php extension then both `.../admin` and `.../admin.php` will be checked
- the fuzz command automatically does GET requests, but you can also fuzz with POST requests, which means you can fuzz for hidden parameters (injection)
    - i.e. `ffuf -w <PATH/wordlist.txt> -u <TARGET/URL>/FUZZ -X POST -d "search=FUZZ"`
        - `-X` sets the request type
        - `-d` is the POST data
