input = open("C:\\Users\\Jacob Sindt\\Documents\\Git\\aoc_2023\\1\\jsindt\\input", "r")
inputfilelines = input.readlines()
codes = []
for lines in range(0, len(inputfilelines)):
    jawn = inputfilelines[lines]
    firstint = 0
    lastint = 0
    for i in range(0,len(jawn)): #goes through each character to see if it is an integer
        if str.isdigit(jawn[i]): #checks if it is an integer
            if firstint == 0:
                firstint = jawn[i]
            else:
                lastint = jawn[i]
    if lastint !=0:
        codes.append(int(f"{firstint}{lastint}"))
    else:
        codes.append(int(f"{firstint}{firstint}"))
sum = 0
for i in range(0, len(codes)):
    print(codes[i])
    sum = sum + codes[i]
print(sum)