These are some, hopefully helpful, notes on the implementation. 

Note zalloc_simple.c is a version  that doesn't allocate more than the
initial tranche.  zalloc.c adds the  ability to request more memory as
we run out of space in the tranches allocated so far.

Typedefs have  been used to  keep the code  a little bit  clearer than
using bare structs.

In this implementation we have chosen to optimise the amount of memory
available by  (partially) overwriting the  block's header when  it has
been allocated.  We don't keep track  of the used memory in a list, so
we can overwrite  the next field.  However, we must  keep track of the
size of the block allocated since this is not provided when the memory
is freed.

This means that  the size field must be the first  field in the header
record and  the next  pointer the  second.  We have  to hope  the user
doesn't overwrite the size field "by accident".

We need  an invariant with respect to  the size of a  block.  Does the
size include the size field itself  or is it the size of the requested
block.

We choose to make the size field the amount of available memory in the
block (excluding the  actual size field).  This makes  the most common
operation --  determining if the block  is large enough --  as fast as
possible.

If the size  were the overall size of the  block including the header,
then we would  constantly have to worry about the  size of the header.
We only need to know this when splitting a block at allocation time.

No  alignment field  has  been  used.  Such  a  field would  guarantee
alignment of the  malloced header to long (presumably  the worst case)
boundary.   However, each  subsequent  header would  also  have to  be
aligned to such a boundary  making the code considerably more complex.
If the  code creates a segmentation violation  or alignment violation,
let me know and we'll use a more complex version.

The list's  root element (start)  is a Header  and not a  HeaderPtr to
make the zalloc function more natural, since it must keep track of the
last node visited, initially this is start.

To  initialise the  system we  need to  create an  initial  tranche of
memory that  can be parcelled out  as calls to zalloc  are made.  This
must  be done  in an  initialise function  since zalloc  cannot easily
determine if it has been done.

Although, perhaps  we can  assume that the  global variable  start has
been initialised  to all zeroes.  Even  if this were the  case, a NULL
test would have  to be performed for each call  to zalloc to determine
if the tranche has been allocated.

This implementation  uses a  first-fit method, doesn't  request blocks
beyond the initial tranche and doesn't coalesce adjacent freed blocks.

Making start a pointer to a  Header block rather than the Header block
itself makes the code seem more logical and orthogonal.

It  is   debatable  whether  MINIMUM_USEFUL_SIZE   should  be  larger.
Currently the block would only be useful for malloc(void*) or smaller.

In  the case  where more  memory  allocated than  requested, the  size
remains correct.   That is the  size of the memory  actually allocated
rather than the requested size.

The  recursive call  of  zalloc made  when  we can't  locate a  useful
blockmust* succeed because we've just added a block of useful size.

