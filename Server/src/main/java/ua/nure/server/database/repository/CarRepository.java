package ua.nure.server.database.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import ua.nure.domain.entity.Car;
import ua.nure.domain.entity.CarBody;
import ua.nure.domain.entity.Engine;
import ua.nure.server.database.ConnectionPool;
import ua.nure.server.exception.ConnectionException;
import ua.nure.server.exception.RepositoryException;

public class CarRepository extends Repository<Car> implements IRepository<Car> {
    private static final StringBuilder SELECT_ALL_CARS = new StringBuilder("SELECT " +
            "CAR.SERIAL_NUMBER AS \"CAR_SERIAL_NUMBER\", " +
            "CAR.PRICE AS \"CAR_PRICE\", " +
            "CAR.BRAND AS \"CAR_BRAND\", " +
            "CAR.CAR_LINE AS \"CAR_LINE\", " +
            "CAR.PHOTO AS \"CAR_PHOTO\", " +
            "CAR.COUNTRY AS \"CAR_COUNTRY\", " +
            "CAR.MANUFACTURE_DATE AS \"CAR_MANUFACTURE_DATE\", " +
            "ENGINE.ID AS \"ENGINE_ID\", " +
            "ENGINE.MODEL AS \"ENGINE_MODEL\", " +
            "ENGINE.VOLUME AS \"ENGINE_VOLUME\", " +
            "ENGINE.BRAND AS \"ENGINE_BRAND\", " +
            "ENGINE.NUMB_VIN_CODE AS \"ENGINE_NUMB_VIN_CODE\", " +
            "ENGINE.eTYPE AS \"ENGINE_eTYPE\", " +
            "CAR_BODY.ID AS \"CAR_BODY_ID\", " +
            "CAR_BODY.MODEL AS \"CAR_BODY_MODEL\", " +
            "CAR_BODY.LENGTH AS \"CAR_BODY_LENGTH\", " +
            "CAR_BODY.WIDTH AS \"CAR_BODY_WIDTH\", " +
            "CAR_BODY.HEIGHT AS \"CAR_BODY_HEIGHT\", " +
            "CAR_BODY.WEIGHT AS \"CAR_BODY_WEIGHT\", " +
            "CAR_BODY.USABILITY AS \"CAR_BODY_USABILITY\", " +
            "CAR_BODY_TYPE.bTYPE AS \"CAR_BODY_TYPE_bTYPE\", " +
            "CAR_BODY_TYPE.NUMB_VIN_CODE AS \"CAR_BODY_TYPE_NUMB_VIN_CODE\", " +
            "CAR_BODY_TYPE.PURPOSE AS \"CAR_BODY_TYPE_PURPOSE\" " +
            "FROM ENGINE,CAR,CAR_BODY,CAR_BODY_TYPE " +
            "WHERE ENGINE.ID = CAR.ID_ENGINE_MODEL AND CAR_BODY.ID_BODY_TYPE = CAR_BODY_TYPE.ID AND CAR.ID_BODY_MODEL=CAR_BODY.ID");

    public CarRepository(ConnectionPool.MyConnection myConnection) {
        super(myConnection);
    }

    @Override
    public List<Car> getAll() throws RepositoryException {
        try (ResultSet resultSet = myConnection.executeQuery(SELECT_ALL_CARS.toString())) {
            return toEntityList(resultSet);
        } catch (SQLException | ConnectionException exception) {
            throw new RepositoryException(exception.getMessage());
        }
    }

    @Override
    public Car getById(Integer id) throws RepositoryException {
        try (ResultSet resultSet = myConnection.executeQuery(SELECT_ALL_CARS.append("\tAND CAR.SERIAL_NUMBER = ").append(id).toString())) {
            return toEntityOrNull(resultSet);
        } catch (SQLException | ConnectionException exception) {
            throw new RepositoryException(exception.getMessage());
        }
    }

    @Override
    protected Car toEntity(ResultSet resultSet) throws SQLException {
        Car.CarBuilder carBuilder = new Car.CarBuilder()
                .setIdentifier(resultSet.getInt("CAR_SERIAL_NUMBER"))
                .setSerialNumber(resultSet.getInt("CAR_SERIAL_NUMBER"))
                .setPrice(resultSet.getInt("CAR_PRICE"))
                .setBrand(resultSet.getString("CAR_BRAND"))
                .setCarLine(resultSet.getString("CAR_LINE"))
                .setPhotoPath(resultSet.getString("CAR_PHOTO"))
                .setManufacturerCountry(resultSet.getString("CAR_COUNTRY"))
                .setManufactureDate(resultSet.getString("CAR_MANUFACTURE_DATE"));

        Engine.EngineBuilder engineBuilder = new Engine.EngineBuilder()
                .setIdentifier(resultSet.getInt("ENGINE_ID"))
                .setModel(resultSet.getString("ENGINE_MODEL"))
                .setVolume(resultSet.getInt("ENGINE_VOLUME"))
                .setBrand(resultSet.getString("ENGINE_BRAND"))
                .setVCode(resultSet.getString("ENGINE_NUMB_VIN_CODE"))
                .setType(resultSet.getString("ENGINE_eTYPE"));

        CarBody.CarBodyBuilder carBodyBuilder = new CarBody.CarBodyBuilder()
                .setIdentifier(resultSet.getInt("CAR_BODY_ID"))
                .setModel(resultSet.getString("CAR_BODY_MODEL"))
                .setLength(resultSet.getInt("CAR_BODY_LENGTH"))
                .setWidth(resultSet.getInt("CAR_BODY_WIDTH"))
                .setHeight(resultSet.getInt("CAR_BODY_HEIGHT"))
                .setWeight(resultSet.getInt("CAR_BODY_WEIGHT"))
                .setUsability(resultSet.getString("CAR_BODY_USABILITY"))
                .setType(resultSet.getString("CAR_BODY_TYPE_bTYPE"))
                .setVCode(resultSet.getString("CAR_BODY_TYPE_NUMB_VIN_CODE"))
                .setPurpose(resultSet.getString("CAR_BODY_TYPE_PURPOSE"));

        return carBuilder.setEngine(engineBuilder.build()).setBody(carBodyBuilder.build()).build();
    }
}
