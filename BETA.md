# EN (Not full)
Use second version, the first one was published just as prototype.
## Main info
This is a Java project which adds Space object with N dimensions (here N is a natural number). Space object can contain (N-1)-dimensional elements, points (for N > 1), lines (for N > 2) and flats (for N > 3).
## Rational
In this project Rational objects are actually rational numbers. Class Rational was made to avoid possible misscalculations with division. To make arithmetic operations on Rational objects you have to use methods because using operators won't work.
## Objects
In space with N dimensions there can be object with (N-1) dimensions - no more, no less (except for point, line and flat). There are two options of making this object:  
The first one is to write parameters of it's geometric equation. For example, a geometric equation of point in 1D space is ax+b=0, of line in 2D space is ax+by+c=0, of flat in 3D space is ax+by+cz+d=0, and so on. It means that of you want to create a flat 2x+9y-5z+11=0, then you have to these parameters: 2, 9, -5, 11.  
The second one is to write coordinates of N points which are in this object. If you're going this way then program will find parameters of this object by using Kramer's rule leaving the last parameter being 1. There's also a possibility that all of these points will be in one (N-2) object. It means that there's no single (N-1)-dimensional object that contains all of these points. This will lead to a thrown NotSingleObjectException.
