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
            symbolPositions.append({'pos': [lineNumber, characterNumber], 'gear': char == '*'})
print(numbers)
print(symbolPositions)
partNumberSum = 0
for pos in symbolPositions:
    if not pos['gear']:
        continue
    gearLineNr = pos['pos'][0]
    gearCharNr = pos['pos'][1]
    touchingNumbers = []
    for number in numbers:
        startAt = number['starts']
        lineNumber = startAt[0]
        charNumber = startAt[1]
        length = number['length']
        partNumber = number['number']
        if gearLineNr - 1 <= lineNumber <= gearLineNr + 1 and charNumber - 1 <= gearCharNr <= charNumber + length:
            touchingNumbers.append(partNumber)
    if len(touchingNumbers) == 2:
        partNumberSum += touchingNumbers[0] * touchingNumbers[1]
print(partNumberSum)
