#include <stdio.h>
#include <stdint.h>
#include <ctype.h>

typedef struct {
    char *str;
    int len;
    int num;
} map_t;

map_t map[] = {
    {"zero", 4, 0},
    {"one", 3, 1},
    {"two", 3, 2},
    {"three", 5, 3},
    {"four", 4, 4},
    {"five", 4, 5},
    {"six", 3, 6},
    {"seven", 5, 7},
    {"eight", 5, 8},
    {"nine", 4, 9}
};

// return the number
int isnumber(char *line, int index, char lr) {
    if(isdigit(line[index])){
        return line[index] - '0';
    }

    int ind = index;
    for(int i = 0; i < 10; i++) {  
        if(lr == 'r') { // from right to left
            for(int j = map[i].len - 1; j >= 0; j--) {
                if(line[ind] == map[i].str[j]){
                    if(j == 0) {
                        return map[i].num;
                    }
                    ind--;
                } else {
                    ind = index;
                    break;
                }
            }
        } else if(lr == 'l') {  // from left to right
            for(int j = 0; j < map[i].len; j++) {
                if(line[ind] == map[i].str[j]) {\
                    if(j == map[i].len - 1) {
                        return map[i].num;
                    }
                    ind++;
                } else {
                    ind = index;
                    break;
                }
            }
        }
    }

    return -1;
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <Filename>\n", argv[0]);
        return 1;
    }

    FILE *f = fopen(argv[1], "r");
    char *line = NULL;
    size_t len = 0;
    ssize_t read;

    uint32_t sum = 0;

    while((read = getline(&line, &len, f)) != -1) {
        uint32_t lp = 0;
        uint32_t rp = read;

        while(isnumber(line, lp, 'l') == -1 || isnumber(line, rp, 'r') == -1) {  
            if(isnumber(line, lp, 'l') == -1) {
                lp++;
            }
            if(isnumber(line, rp, 'r') == -1){
                rp--;
            }
        }

        printf("Line: %sDigits: %d %d \n", line, isnumber(line, lp, 'l'), isnumber(line, rp, 'r'));
        sum += isnumber(line, lp, 'l')*10 + isnumber(line, rp, 'r');
    }

    printf("Sum: %d\n", sum);

    fclose(f);
    return 0;
}
