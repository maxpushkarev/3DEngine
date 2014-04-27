/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

/**
 * Class Face.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Face implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id.
     */
    private int _id;

    /**
     * keeps track of state for field: _id
     */
    private boolean _has_id;

    /**
     * Field _pointIdList.
     */
    private java.util.Vector<java.lang.Integer> _pointIdList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Face() {
        super();
        this._pointIdList = new java.util.Vector<java.lang.Integer>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vPointId
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addPointId(
            final int vPointId)
    throws java.lang.IndexOutOfBoundsException {
        // check for the maximum size
        if (this._pointIdList.size() >= 3) {
            throw new IndexOutOfBoundsException("addPointId has a maximum of 3");
        }

        this._pointIdList.addElement(new java.lang.Integer(vPointId));
    }

    /**
     * 
     * 
     * @param index
     * @param vPointId
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addPointId(
            final int index,
            final int vPointId)
    throws java.lang.IndexOutOfBoundsException {
        // check for the maximum size
        if (this._pointIdList.size() >= 3) {
            throw new IndexOutOfBoundsException("addPointId has a maximum of 3");
        }

        this._pointIdList.add(index, new java.lang.Integer(vPointId));
    }

    /**
     */
    public void deleteId(
    ) {
        this._has_id= false;
    }

    /**
     * Method enumeratePointId.
     * 
     * @return an Enumeration over all int elements
     */
    public java.util.Enumeration<java.lang.Integer> enumeratePointId(
    ) {
        return this._pointIdList.elements();
    }

    /**
     * Returns the value of field 'id'.
     * 
     * @return the value of field 'Id'.
     */
    public int getId(
    ) {
        return this._id;
    }

    /**
     * Method getPointId.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the int at the given index
     */
    public int getPointId(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._pointIdList.size()) {
            throw new IndexOutOfBoundsException("getPointId: Index value '" + index + "' not in range [0.." + (this._pointIdList.size() - 1) + "]");
        }

        return ((java.lang.Integer) _pointIdList.get(index)).intValue();
    }

    /**
     * Method getPointId.Returns the contents of the collection in
     * an Array.  
     * 
     * @return this collection as an Array
     */
    public int[] getPointId(
    ) {
        int size = this._pointIdList.size();
        int[] array = new int[size];
        java.util.Iterator iter = _pointIdList.iterator();
        for (int index = 0; index < size; index++) {
            array[index] = ((java.lang.Integer) iter.next()).intValue();
        }
        return array;
    }

    /**
     * Method getPointIdCount.
     * 
     * @return the size of this collection
     */
    public int getPointIdCount(
    ) {
        return this._pointIdList.size();
    }

    /**
     * Method hasId.
     * 
     * @return true if at least one Id has been added
     */
    public boolean hasId(
    ) {
        return this._has_id;
    }

    /**
     */
    public void removeAllPointId(
    ) {
        this._pointIdList.clear();
    }

    /**
     * Method removePointId.
     * 
     * @param vPointId
     * @return true if the object was removed from the collection.
     */
    public boolean removePointId(
            final int vPointId) {
        boolean removed = _pointIdList.remove(new java.lang.Integer(vPointId));
        return removed;
    }

    /**
     * Method removePointIdAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public int removePointIdAt(
            final int index) {
        java.lang.Object obj = this._pointIdList.remove(index);
        return ((java.lang.Integer) obj).intValue();
    }

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(
            final int id) {
        this._id = id;
        this._has_id = true;
    }

    /**
     * 
     * 
     * @param index
     * @param vPointId
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setPointId(
            final int index,
            final int vPointId)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._pointIdList.size()) {
            throw new IndexOutOfBoundsException("setPointId: Index value '" + index + "' not in range [0.." + (this._pointIdList.size() - 1) + "]");
        }

        this._pointIdList.set(index, new java.lang.Integer(vPointId));
    }

    /**
     * 
     * 
     * @param vPointIdArray
     */
    public void setPointId(
            final int[] vPointIdArray) {
        //-- copy array
        _pointIdList.clear();

        for (int i = 0; i < vPointIdArray.length; i++) {
                this._pointIdList.add(new java.lang.Integer(vPointIdArray[i]));
        }
    }

}
