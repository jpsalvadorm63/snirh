package base



import org.junit.*
import grails.test.mixin.*

@TestFor(TipoEstacionController)
@Mock(TipoEstacion)
class TipoEstacionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tipoEstacion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tipoEstacionInstanceList.size() == 0
        assert model.tipoEstacionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.tipoEstacionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tipoEstacionInstance != null
        assert view == '/tipoEstacion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tipoEstacion/show/1'
        assert controller.flash.message != null
        assert TipoEstacion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoEstacion/list'

        populateValidParams(params)
        def tipoEstacion = new TipoEstacion(params)

        assert tipoEstacion.save() != null

        params.id = tipoEstacion.id

        def model = controller.show()

        assert model.tipoEstacionInstance == tipoEstacion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoEstacion/list'

        populateValidParams(params)
        def tipoEstacion = new TipoEstacion(params)

        assert tipoEstacion.save() != null

        params.id = tipoEstacion.id

        def model = controller.edit()

        assert model.tipoEstacionInstance == tipoEstacion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoEstacion/list'

        response.reset()

        populateValidParams(params)
        def tipoEstacion = new TipoEstacion(params)

        assert tipoEstacion.save() != null

        // test invalid parameters in update
        params.id = tipoEstacion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tipoEstacion/edit"
        assert model.tipoEstacionInstance != null

        tipoEstacion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tipoEstacion/show/$tipoEstacion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tipoEstacion.clearErrors()

        populateValidParams(params)
        params.id = tipoEstacion.id
        params.version = -1
        controller.update()

        assert view == "/tipoEstacion/edit"
        assert model.tipoEstacionInstance != null
        assert model.tipoEstacionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tipoEstacion/list'

        response.reset()

        populateValidParams(params)
        def tipoEstacion = new TipoEstacion(params)

        assert tipoEstacion.save() != null
        assert TipoEstacion.count() == 1

        params.id = tipoEstacion.id

        controller.delete()

        assert TipoEstacion.count() == 0
        assert TipoEstacion.get(tipoEstacion.id) == null
        assert response.redirectedUrl == '/tipoEstacion/list'
    }
}
