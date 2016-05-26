package base



import org.junit.*
import grails.test.mixin.*

@TestFor(EstadoEstacionController)
@Mock(EstadoEstacion)
class EstadoEstacionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/estadoEstacion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.estadoEstacionInstanceList.size() == 0
        assert model.estadoEstacionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.estadoEstacionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.estadoEstacionInstance != null
        assert view == '/estadoEstacion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/estadoEstacion/show/1'
        assert controller.flash.message != null
        assert EstadoEstacion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/estadoEstacion/list'

        populateValidParams(params)
        def estadoEstacion = new EstadoEstacion(params)

        assert estadoEstacion.save() != null

        params.id = estadoEstacion.id

        def model = controller.show()

        assert model.estadoEstacionInstance == estadoEstacion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/estadoEstacion/list'

        populateValidParams(params)
        def estadoEstacion = new EstadoEstacion(params)

        assert estadoEstacion.save() != null

        params.id = estadoEstacion.id

        def model = controller.edit()

        assert model.estadoEstacionInstance == estadoEstacion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/estadoEstacion/list'

        response.reset()

        populateValidParams(params)
        def estadoEstacion = new EstadoEstacion(params)

        assert estadoEstacion.save() != null

        // test invalid parameters in update
        params.id = estadoEstacion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/estadoEstacion/edit"
        assert model.estadoEstacionInstance != null

        estadoEstacion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/estadoEstacion/show/$estadoEstacion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        estadoEstacion.clearErrors()

        populateValidParams(params)
        params.id = estadoEstacion.id
        params.version = -1
        controller.update()

        assert view == "/estadoEstacion/edit"
        assert model.estadoEstacionInstance != null
        assert model.estadoEstacionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/estadoEstacion/list'

        response.reset()

        populateValidParams(params)
        def estadoEstacion = new EstadoEstacion(params)

        assert estadoEstacion.save() != null
        assert EstadoEstacion.count() == 1

        params.id = estadoEstacion.id

        controller.delete()

        assert EstadoEstacion.count() == 0
        assert EstadoEstacion.get(estadoEstacion.id) == null
        assert response.redirectedUrl == '/estadoEstacion/list'
    }
}
