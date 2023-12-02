# frozen_string_literal: true

if ARGV.length != 1
  puts 'please pass 1 and only one arg as path to the file to read from'
  return
end
path = ARGV[0]
puts "using path \"#{path}\" as inputFile"
unless File.file?(path)
  puts "cannot find file at #{path}"
  return
end
file = File.open(path)
number_map = {
  '1': 1,
  '2': 2,
  '3': 3,
  '4': 4,
  '5': 5,
  '6': 6,
  '7': 7,
  '8': 8,
  '9': 9,
  'one': 1,
  'two': 2,
  'three': 3,
  'four': 4,
  'five': 5,
  'six': 6,
  'seven': 7,
  'eight': 8,
  'nine': 9,
}
line_numbers = []
file.readlines.each do |line|
  found_numbers_on_line = {}
  number_map.each_key do |key|
    line.index
    key_index = line.index key.to_s
    next if key_index.nil?
    found_numbers_on_line[key_index] = number_map[key]
    key_rindex = line.rindex key.to_s
    next if key_rindex.nil?
    found_numbers_on_line[key_rindex] = number_map[key]
  end
  puts line
  puts "found numbers: '#{found_numbers_on_line}'"
  sorted_found_index = found_numbers_on_line.keys.sort!
  line_number = "#{found_numbers_on_line[sorted_found_index.first]}#{found_numbers_on_line[sorted_found_index.last]}".to_i
  puts "line_number is: '#{line_number}'"
  line_numbers << line_number
end
sum = line_numbers.sum
puts "summed #{line_numbers.length} entries"
puts "sum is: #{sum}"
