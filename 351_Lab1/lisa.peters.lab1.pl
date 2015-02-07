######################################### 	
#    CSCI 305 - Programming Lab #1		
#										
# Lisa Peters				
#  lisapeters.peters@gmail.com		
#										
#########################################

# Replace the string value of the following variable with your names.
my $name = "Lisa Peters";
my $partner = "Janette Rounds";
print "CSCI 305 Lab 1 submitted by $name and $partner.\n\n";

# Checks for the argument, fail if none given
if($#ARGV != 0) {
    print STDERR "You must specify the file name as the argument.\n";
    exit 4;
}

# Opens the file and assign it to handle INFILE
open(INFILE, $ARGV[0]) or die "Cannot open $ARGV[0]: $!.\n";


# YOUR VARIABLE DEFINITIONS HERE...
$i = 0; 
# This loops through each line of the file
while($line = <INFILE>) {

        # Prints Each Song Title
        my @fields = split /<SEP>/, $line;
        my $title = $fields[3];
        $title =~ s/\(.+/ /g;# Remove (etc.
		$title =~ s/\[.+/ /g;# Remove [etc.
		$title =~ s/\{.+/ /g;# Remove {etc.
		$title =~ s/\\.+/ /g;# Remove \etc.
		$title =~ s/\/.+/ /g;# Remove /etc.
		$title =~ s/_.+/ /g; # Remove _etc.
		$title =~ s/\-.+/ /g;# Remove -etc.
		$title =~ s/:.+/ /g; # Remove :etc.
		$title =~ s/\".+/ /g;# Remove "etc.
		$title =~ s/\`.+/ /g;# Remove 'etc.
		$title =~ s/\+.+/ /g;# Remove +etc.
		$title =~ s/=.+/ /g; # Remove =etc.
		$title =~ s/\*.+/ /g;# Remove *etc.
		$title =~ s/feat\..+/ /g;# Remove feat.etc.
		
		$title =~ s/&/ /g; #Replace & with space
		$title =~ s/\?/ /g; #Replace ? with space
		$title =~ s/!/ /g; #Replace ! with space
		$title =~ s/\./ /g; #Replace . with space
		$title =~ s/\;/ /g; #Replace ; with space
		$title =~ s/\$/ /g; #Replace $ with space
		$title =~ s/\@/ /g; #Replace @ with space
		$title =~ s/%/ /g; #Replace % with space
		$title =~ s/\#/ /g; #Replace # with space
		$title =~ s/\|/ /g; #Replace | with space
		$title =~ s/¿/ /g; #Replace ¿ with space
		$title =~ s/¡/ /g; #Replace ¡ with space
        
        #\w+ 31231: |('\w+) 31246
        
        if ($title =~ m/[^[:ascii:]]/){
        	#Don't print unicode characters		
			}
        else{ 
        	$title = lc $title; 
        	print "$title";
        	$i += 1;
        	
		}
	
}

# self-check print "$i . "\n" ; 
# Close the file handle
close INFILE; 

# At this point (hopefully) you will have finished processing the song 
# title file and have populated your data structure of bigram counts.
print "File parsed. Bigram model built.\n\n";


# User control loop
print "Enter a word [Enter 'q' to quit]: ";
$input = <STDIN>;
chomp($input);
print "\n";	
while ($input ne "q"){
	# Replace these lines with some useful code
	print "Not yet implemented.  Goodbye.\n";
	$input = 'q';
}

# MORE OF YOUR CODE HERE....
