/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

/**
 * Class Objects.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Objects implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _objectList.
     */
    private java.util.Vector<Object> _objectList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Objects() {
        super();
        this._objectList = new java.util.Vector<Object>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addObject(
            final Object vObject)
    throws java.lang.IndexOutOfBoundsException {
        this._objectList.addElement(vObject);
    }

    /**
     * 
     * 
     * @param index
     * @param vObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addObject(
            final int index,
            final Object vObject)
    throws java.lang.IndexOutOfBoundsException {
        this._objectList.add(index, vObject);
    }

    /**
     * Method enumerateObject.
     * 
     * @return an Enumeration over all Object elements
     */
    public java.util.Enumeration<? extends Object> enumerateObject(
    ) {
        return this._objectList.elements();
    }

    /**
     * Method getObject.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the Object at the given index
     */
    public Object getObject(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._objectList.size()) {
            throw new IndexOutOfBoundsException("getObject: Index value '" + index + "' not in range [0.." + (this._objectList.size() - 1) + "]");
        }

        return (Object) _objectList.get(index);
    }

    /**
     * Method getObject.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public Object[] getObject(
    ) {
        Object[] array = new Object[0];
        return (Object[]) this._objectList.toArray(array);
    }

    /**
     * Method getObjectCount.
     * 
     * @return the size of this collection
     */
    public int getObjectCount(
    ) {
        return this._objectList.size();
    }

    /**
     */
    public void removeAllObject(
    ) {
        this._objectList.clear();
    }

    /**
     * Method removeObject.
     * 
     * @param vObject
     * @return true if the object was removed from the collection.
     */
    public boolean removeObject(
            final Object vObject) {
        boolean removed = _objectList.remove(vObject);
        return removed;
    }

    /**
     * Method removeObjectAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public Object removeObjectAt(
            final int index) {
        java.lang.Object obj = this._objectList.remove(index);
        return (Object) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setObject(
            final int index,
            final Object vObject)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._objectList.size()) {
            throw new IndexOutOfBoundsException("setObject: Index value '" + index + "' not in range [0.." + (this._objectList.size() - 1) + "]");
        }

        this._objectList.set(index, vObject);
    }

    /**
     * 
     * 
     * @param vObjectArray
     */
    public void setObject(
            final Object[] vObjectArray) {
        //-- copy array
        _objectList.clear();

        for (int i = 0; i < vObjectArray.length; i++) {
                this._objectList.add(vObjectArray[i]);
        }
    }

}
