/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

/**
 * Class Faces.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Faces implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _faceList.
     */
    private java.util.Vector<Face> _faceList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Faces() {
        super();
        this._faceList = new java.util.Vector<Face>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vFace
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFace(
            final Face vFace)
    throws java.lang.IndexOutOfBoundsException {
        this._faceList.addElement(vFace);
    }

    /**
     * 
     * 
     * @param index
     * @param vFace
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFace(
            final int index,
            final Face vFace)
    throws java.lang.IndexOutOfBoundsException {
        this._faceList.add(index, vFace);
    }

    /**
     * Method enumerateFace.
     * 
     * @return an Enumeration over all Face elements
     */
    public java.util.Enumeration<? extends Face> enumerateFace(
    ) {
        return this._faceList.elements();
    }

    /**
     * Method getFace.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the Face at the given index
     */
    public Face getFace(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._faceList.size()) {
            throw new IndexOutOfBoundsException("getFace: Index value '" + index + "' not in range [0.." + (this._faceList.size() - 1) + "]");
        }

        return (Face) _faceList.get(index);
    }

    /**
     * Method getFace.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public Face[] getFace(
    ) {
        Face[] array = new Face[0];
        return (Face[]) this._faceList.toArray(array);
    }

    /**
     * Method getFaceCount.
     * 
     * @return the size of this collection
     */
    public int getFaceCount(
    ) {
        return this._faceList.size();
    }

    /**
     */
    public void removeAllFace(
    ) {
        this._faceList.clear();
    }

    /**
     * Method removeFace.
     * 
     * @param vFace
     * @return true if the object was removed from the collection.
     */
    public boolean removeFace(
            final Face vFace) {
        boolean removed = _faceList.remove(vFace);
        return removed;
    }

    /**
     * Method removeFaceAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public Face removeFaceAt(
            final int index) {
        java.lang.Object obj = this._faceList.remove(index);
        return (Face) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vFace
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setFace(
            final int index,
            final Face vFace)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._faceList.size()) {
            throw new IndexOutOfBoundsException("setFace: Index value '" + index + "' not in range [0.." + (this._faceList.size() - 1) + "]");
        }

        this._faceList.set(index, vFace);
    }

    /**
     * 
     * 
     * @param vFaceArray
     */
    public void setFace(
            final Face[] vFaceArray) {
        //-- copy array
        _faceList.clear();

        for (int i = 0; i < vFaceArray.length; i++) {
                this._faceList.add(vFaceArray[i]);
        }
    }

}
