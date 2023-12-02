# frozen_string_literal: true

if ARGV.length != 1
  printf 'please pass 1 and only one arg as path to the file to read from'
  return
end
path = ARGV[0]
printf "using path \"#{path}\" as inputFile"
unless File.file?(path)
  puts
  printf "cannot find file at #{path}"
  return
end
file = File.open(path)
number_strings = %w[1 2 3 4 5 6 7 8 9]
spelled_number_strings = %w[one two three four five six seven eight nine]
line_numbers = []
other_sum = 0
file.readlines.each_with_index do |line, index|
  digits = ''
  buffer = ''
  # puts "line #{index}: #{line}"
  line.each_char do |char|
    # puts "current char: #{char}"
    number_index = number_strings.find_index char
    if number_index.nil?
      buffer += char
      # puts "char is not a number, buffer is now: #{buffer}"
      spelled_number_strings.each_with_index do |spelled_number, no_i|
        next if buffer.index(spelled_number).nil?

        # puts "found spelled number: #{buffer}, add #{no_i} to digits, digits is now: #{digits}"
        number_to_add = no_i + 1
        digits += number_to_add.to_s
        buffer = ''
      end
    else
      # puts "found #{char} in number_strings at index #{number_index}"
      digits += char
      buffer = ''
      # puts "reset buffer digits is now: #{digits}"
    end
  end
  puts "digits are: #{digits}"
  line_number = digits.chars[0] + digits.chars[digits.length - 1]
  puts "line number: #{line_number}"
  line_int = line_number.to_i
  other_sum += line_int
  line_numbers << line_int
end
sum = line_numbers.sum
puts "summed #{line_numbers.length} entries"
puts "sum is: #{sum}"
puts "other_sum is: #{other_sum}"
