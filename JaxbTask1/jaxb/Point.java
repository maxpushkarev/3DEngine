/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

/**
 * Class Point.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Point implements java.io.Serializable {


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
     * Field _x.
     */
    private double _x;

    /**
     * keeps track of state for field: _x
     */
    private boolean _has_x;

    /**
     * Field _y.
     */
    private double _y;

    /**
     * keeps track of state for field: _y
     */
    private boolean _has_y;

    /**
     * Field _z.
     */
    private double _z;

    /**
     * keeps track of state for field: _z
     */
    private boolean _has_z;


      //----------------/
     //- Constructors -/
    //----------------/

    public Point() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteId(
    ) {
        this._has_id= false;
    }

    /**
     */
    public void deleteX(
    ) {
        this._has_x= false;
    }

    /**
     */
    public void deleteY(
    ) {
        this._has_y= false;
    }

    /**
     */
    public void deleteZ(
    ) {
        this._has_z= false;
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
     * Returns the value of field 'x'.
     * 
     * @return the value of field 'X'.
     */
    public double getX(
    ) {
        return this._x;
    }

    /**
     * Returns the value of field 'y'.
     * 
     * @return the value of field 'Y'.
     */
    public double getY(
    ) {
        return this._y;
    }

    /**
     * Returns the value of field 'z'.
     * 
     * @return the value of field 'Z'.
     */
    public double getZ(
    ) {
        return this._z;
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
     * Method hasX.
     * 
     * @return true if at least one X has been added
     */
    public boolean hasX(
    ) {
        return this._has_x;
    }

    /**
     * Method hasY.
     * 
     * @return true if at least one Y has been added
     */
    public boolean hasY(
    ) {
        return this._has_y;
    }

    /**
     * Method hasZ.
     * 
     * @return true if at least one Z has been added
     */
    public boolean hasZ(
    ) {
        return this._has_z;
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
     * Sets the value of field 'x'.
     * 
     * @param x the value of field 'x'.
     */
    public void setX(
            final double x) {
        this._x = x;
        this._has_x = true;
    }

    /**
     * Sets the value of field 'y'.
     * 
     * @param y the value of field 'y'.
     */
    public void setY(
            final double y) {
        this._y = y;
        this._has_y = true;
    }

    /**
     * Sets the value of field 'z'.
     * 
     * @param z the value of field 'z'.
     */
    public void setZ(
            final double z) {
        this._z = z;
        this._has_z = true;
    }

}
