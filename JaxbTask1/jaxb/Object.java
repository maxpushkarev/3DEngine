/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

/**
 * Class Object.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Object implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name.
     */
    private java.lang.String _name;

    /**
     * Field _faceIdList.
     */
    private java.util.Vector<java.lang.Integer> _faceIdList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Object() {
        super();
        this._faceIdList = new java.util.Vector<java.lang.Integer>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vFaceId
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFaceId(
            final int vFaceId)
    throws java.lang.IndexOutOfBoundsException {
        this._faceIdList.addElement(new java.lang.Integer(vFaceId));
    }

    /**
     * 
     * 
     * @param index
     * @param vFaceId
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFaceId(
            final int index,
            final int vFaceId)
    throws java.lang.IndexOutOfBoundsException {
        this._faceIdList.add(index, new java.lang.Integer(vFaceId));
    }

    /**
     * Method enumerateFaceId.
     * 
     * @return an Enumeration over all int elements
     */
    public java.util.Enumeration<java.lang.Integer> enumerateFaceId(
    ) {
        return this._faceIdList.elements();
    }

    /**
     * Method getFaceId.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the int at the given index
     */
    public int getFaceId(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._faceIdList.size()) {
            throw new IndexOutOfBoundsException("getFaceId: Index value '" + index + "' not in range [0.." + (this._faceIdList.size() - 1) + "]");
        }

        return ((java.lang.Integer) _faceIdList.get(index)).intValue();
    }

    /**
     * Method getFaceId.Returns the contents of the collection in
     * an Array.  
     * 
     * @return this collection as an Array
     */
    public int[] getFaceId(
    ) {
        int size = this._faceIdList.size();
        int[] array = new int[size];
        java.util.Iterator iter = _faceIdList.iterator();
        for (int index = 0; index < size; index++) {
            array[index] = ((java.lang.Integer) iter.next()).intValue();
        }
        return array;
    }

    /**
     * Method getFaceIdCount.
     * 
     * @return the size of this collection
     */
    public int getFaceIdCount(
    ) {
        return this._faceIdList.size();
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName(
    ) {
        return this._name;
    }

    /**
     */
    public void removeAllFaceId(
    ) {
        this._faceIdList.clear();
    }

    /**
     * Method removeFaceId.
     * 
     * @param vFaceId
     * @return true if the object was removed from the collection.
     */
    public boolean removeFaceId(
            final int vFaceId) {
        boolean removed = _faceIdList.remove(new java.lang.Integer(vFaceId));
        return removed;
    }

    /**
     * Method removeFaceIdAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public int removeFaceIdAt(
            final int index) {
        java.lang.Object obj = this._faceIdList.remove(index);
        return ((java.lang.Integer) obj).intValue();
    }

    /**
     * 
     * 
     * @param index
     * @param vFaceId
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setFaceId(
            final int index,
            final int vFaceId)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._faceIdList.size()) {
            throw new IndexOutOfBoundsException("setFaceId: Index value '" + index + "' not in range [0.." + (this._faceIdList.size() - 1) + "]");
        }

        this._faceIdList.set(index, new java.lang.Integer(vFaceId));
    }

    /**
     * 
     * 
     * @param vFaceIdArray
     */
    public void setFaceId(
            final int[] vFaceIdArray) {
        //-- copy array
        _faceIdList.clear();

        for (int i = 0; i < vFaceIdArray.length; i++) {
                this._faceIdList.add(new java.lang.Integer(vFaceIdArray[i]));
        }
    }

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(
            final java.lang.String name) {
        this._name = name;
    }

}
