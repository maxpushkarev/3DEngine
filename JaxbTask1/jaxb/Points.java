/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

/**
 * Class Points.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Points implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _pointList.
     */
    private java.util.Vector<Point> _pointList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Points() {
        super();
        this._pointList = new java.util.Vector<Point>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vPoint
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addPoint(
            final Point vPoint)
    throws java.lang.IndexOutOfBoundsException {
        this._pointList.addElement(vPoint);
    }

    /**
     * 
     * 
     * @param index
     * @param vPoint
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addPoint(
            final int index,
            final Point vPoint)
    throws java.lang.IndexOutOfBoundsException {
        this._pointList.add(index, vPoint);
    }

    /**
     * Method enumeratePoint.
     * 
     * @return an Enumeration over all Point elements
     */
    public java.util.Enumeration<? extends Point> enumeratePoint(
    ) {
        return this._pointList.elements();
    }

    /**
     * Method getPoint.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the Point at the given index
     */
    public Point getPoint(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._pointList.size()) {
            throw new IndexOutOfBoundsException("getPoint: Index value '" + index + "' not in range [0.." + (this._pointList.size() - 1) + "]");
        }

        return (Point) _pointList.get(index);
    }

    /**
     * Method getPoint.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public Point[] getPoint(
    ) {
        Point[] array = new Point[0];
        return (Point[]) this._pointList.toArray(array);
    }

    /**
     * Method getPointCount.
     * 
     * @return the size of this collection
     */
    public int getPointCount(
    ) {
        return this._pointList.size();
    }

    /**
     */
    public void removeAllPoint(
    ) {
        this._pointList.clear();
    }

    /**
     * Method removePoint.
     * 
     * @param vPoint
     * @return true if the object was removed from the collection.
     */
    public boolean removePoint(
            final Point vPoint) {
        boolean removed = _pointList.remove(vPoint);
        return removed;
    }

    /**
     * Method removePointAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public Point removePointAt(
            final int index) {
        java.lang.Object obj = this._pointList.remove(index);
        return (Point) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vPoint
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setPoint(
            final int index,
            final Point vPoint)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._pointList.size()) {
            throw new IndexOutOfBoundsException("setPoint: Index value '" + index + "' not in range [0.." + (this._pointList.size() - 1) + "]");
        }

        this._pointList.set(index, vPoint);
    }

    /**
     * 
     * 
     * @param vPointArray
     */
    public void setPoint(
            final Point[] vPointArray) {
        //-- copy array
        _pointList.clear();

        for (int i = 0; i < vPointArray.length; i++) {
                this._pointList.add(vPointArray[i]);
        }
    }

}
