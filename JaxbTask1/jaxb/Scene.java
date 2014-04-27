/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

/**
 * Class Scene.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Scene implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _points.
     */
    private Points _points;

    /**
     * Field _faces.
     */
    private Faces _faces;

    /**
     * Field _objects.
     */
    private Objects _objects;


      //----------------/
     //- Constructors -/
    //----------------/

    public Scene() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'faces'.
     * 
     * @return the value of field 'Faces'.
     */
    public Faces getFaces(
    ) {
        return this._faces;
    }

    /**
     * Returns the value of field 'objects'.
     * 
     * @return the value of field 'Objects'.
     */
    public Objects getObjects(
    ) {
        return this._objects;
    }

    /**
     * Returns the value of field 'points'.
     * 
     * @return the value of field 'Points'.
     */
    public Points getPoints(
    ) {
        return this._points;
    }

    /**
     * Sets the value of field 'faces'.
     * 
     * @param faces the value of field 'faces'.
     */
    public void setFaces(
            final Faces faces) {
        this._faces = faces;
    }

    /**
     * Sets the value of field 'objects'.
     * 
     * @param objects the value of field 'objects'.
     */
    public void setObjects(
            final Objects objects) {
        this._objects = objects;
    }

    /**
     * Sets the value of field 'points'.
     * 
     * @param points the value of field 'points'.
     */
    public void setPoints(
            final Points points) {
        this._points = points;
    }

}
