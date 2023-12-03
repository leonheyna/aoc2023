numbers = []
symbolPositions = []
file = open("./../input")
buffer = ''
startsAt = []
for lineNumber, line in enumerate(file):
    for characterNumber, char in enumerate(line):
        if char.isdigit():
            # part of partNumber
            if not buffer:
                startsAt = [lineNumber, characterNumber]
            buffer += char
        elif char == '.' or char == '\n':
            # end current buffer, skip
            if buffer:
                number = {'number': int(buffer), 'starts': startsAt, 'length': len(buffer)}
                numbers.append(number)
                buffer = ''
        else:
            if buffer:
                number = {'number': int(buffer), 'starts': startsAt, 'length': len(buffer)}
                numbers.append(number)
                buffer = ''
            # this is a symbol
            symbolPositions.append([lineNumber, characterNumber])
print(numbers)
print(symbolPositions)
partNumberSum = 0
for number in numbers:
    for pos in symbolPositions:
        startAt = number['starts']
        lineNumber = startAt[0]
        charNumber = startAt[1]
        length = number['length']
        partNumber = number['number']
        if pos[0] - 1 <= lineNumber <= pos[0] + 1 and charNumber - 1 <= pos[1] <= charNumber + length:
            print("add "+str(partNumber))
            partNumberSum += partNumber
            break
print(partNumberSum)
