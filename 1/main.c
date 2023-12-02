#include <stdio.h>
#include <stdint.h>

typedef struct {
    char *str;
    int i;
} map_t;

map_t map[] = {
    {"zero", 0},
    {"one", 1},
    {"two", 2},
    {"three", 3},
    {"four", 4},
    {"five", 5},
    {"six", 6},
    {"seven", 7},
    {"eight", 8},
    {"nine", 9}
};

int isdigit(chat *l) {
    
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
        uint32_t rp = read; // \0 and \n at end of each line

        while(!(isdigit(line[lp]) && isdigit(line[rp]))) {  
            if(!isdigit(line[lp])) {
                lp++;
            }
            if(!isdigit(line[rp])){
                rp--;
            }
        }

        printf("Line: %sDigits: %d %d \n", line, line[lp] - '0', line[rp] - '0');
        sum += ((line[lp] - '0')*10 + (line[rp] - '0'));
    }

    printf("Sum: %d\n", sum);

    fclose(f);
    return 0;
}