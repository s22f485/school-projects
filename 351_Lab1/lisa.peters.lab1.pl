######################################### 	
#    CSCI 305 - Programming Lab #1		
#										
# Lisa Peters				
#  lisapeters.peters@gmail.com	
#	janette.rounds@msu.montana.edu	
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
my $i = 0; #self check variable
my %Hoh = (); #hash of hashes

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
        
        if ($title =~ m/[^[:ascii:]]/){
        	#Don't print unicode characters		
			}
        else{ #if not an unicode characters
			#convert all chars to lowercase
        	$title = lc $title;  
			#put each line into an array of words, splitting based on spaces
        	my @bigram = split /\s/, $title;
       			
       		#putting words and word frequencies into nested hash
        	for(my $j = 0; $j <= $#bigram -1 ;$j++){  
				#check if a, an, and, by, for, from, in, of, on, or, out, the, to, with
				if(@bigram[$j] ne "the" && @bigram[$j] ne "a" && @bigram[$j] ne "an" && @bigram[$j] ne "and" && @bigram[$j] ne "by" &&
						@bigram[$j] ne "for" && @bigram[$j] ne "from" && @bigram[$j] ne "in"&& @bigram[$j] ne "of" && @bigram[$j] ne "on" && 
						@bigram[$j] ne "or" && @bigram[$j] ne "out" && @bigram[$j] ne "to" && @bigram[$j] ne "with"){
					if (exists $Hoh{@bigram[$j]} && defined $Hoh{@bigram[$j]}{@bigram[$j+1]}){
						$Hoh{@bigram[$j]}{@bigram[$j+1]} ++; 
					} else{
						$Hoh {@bigram[$j]} {@bigram[$j+1]} = 1; 
					}
				}
        	}
        	      	   	
        	$i += 1; #self-check variable
        	
		}
	
}

#printing out each bigram
foreach $item(sort keys %Hoh){

	print "$item: "; 
	foreach $iteminitem (keys %{$Hoh{$item}}){
		print "$iteminitem = $Hoh{$item}{$iteminitem} " ; 
		}
	print "\n"; 
	}
# Close the file handle
close INFILE; 

print "File parsed. Bigram model built.\n\n";

my $userinput = getin();
#while user doesn't want to quit, create song titles based on userinput.
while ($userinput ne "q"){
	#title will be outstring
	my $outstring;
	#most is a variable to track word returned by mcw().
	my $most = mcw($userinput);
	# Call most common word to string together song title
	
	#if mcw returns the same word, the input has no following words
	if($most ne $userinput){
		$outstring = $userinput." ".$most;
	} else {
		$outstring = $userinput;
	}
	#infinite loop through following words, but it should break when we have no following words
	for(my $q = 1; $q < 20; $q){
		my $temp = mcw($most);
		if($temp ne $most){
			$most = $temp;
			$outstring = $outstring." ".$most;
		} else {
			#breaking loop
			last;
		}
	}
	print "$outstring"."\n";
	$userinput = getin();
}
# User control loop
sub getin{
	print "Enter a word [Enter 'q' to quit]: ";
	$input = <STDIN>;
	chomp($input);
	print "\n";	
	return $input;
}
# most common word function
sub mcw {
	#@_ is string passed into subroutine
	my ($text) = @_;
	my $val = 1;
	my $word = $text;
	#if word exists in hash of hash, loop through following words
	if (exists $Hoh{$text}){
		
		
		foreach $var (keys %{$Hoh{$text}}){
			#if this following word has higher frequency than current $word, replace.
			if($val < $Hoh{$text}{$var}){
				$val = $Hoh{$text}{$var};
				$word = $var;
		
			} elsif($val == $Hoh{$text}{$var}){
				#Otherwise if two following words are equal, pick one randomly
				my $t = int(rand(2));
				if($t >0){
					$val = $Hoh{$text}{$var};
					$word = $var;
		
				}
			}
		}
	}
	#will return the starting argument if there are no following words.
	return $word;
}
