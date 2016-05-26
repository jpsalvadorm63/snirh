package base



import org.junit.*
import grails.test.mixin.*

@TestFor(EstadisticaController)
@Mock(Estadistica)
class EstadisticaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/estadistica/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.estadisticaInstanceList.size() == 0
        assert model.estadisticaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.estadisticaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.estadisticaInstance != null
        assert view == '/estadistica/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/estadistica/show/1'
        assert controller.flash.message != null
        assert Estadistica.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/estadistica/list'

        populateValidParams(params)
        def estadistica = new Estadistica(params)

        assert estadistica.save() != null

        params.id = estadistica.id

        def model = controller.show()

        assert model.estadisticaInstance == estadistica
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/estadistica/list'

        populateValidParams(params)
        def estadistica = new Estadistica(params)

        assert estadistica.save() != null

        params.id = estadistica.id

        def model = controller.edit()

        assert model.estadisticaInstance == estadistica
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/estadistica/list'

        response.reset()

        populateValidParams(params)
        def estadistica = new Estadistica(params)

        assert estadistica.save() != null

        // test invalid parameters in update
        params.id = estadistica.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/estadistica/edit"
        assert model.estadisticaInstance != null

        estadistica.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/estadistica/show/$estadistica.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        estadistica.clearErrors()

        populateValidParams(params)
        params.id = estadistica.id
        params.version = -1
        controller.update()

        assert view == "/estadistica/edit"
        assert model.estadisticaInstance != null
        assert model.estadisticaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/estadistica/list'

        response.reset()

        populateValidParams(params)
        def estadistica = new Estadistica(params)

        assert estadistica.save() != null
        assert Estadistica.count() == 1

        params.id = estadistica.id

        controller.delete()

        assert Estadistica.count() == 0
        assert Estadistica.get(estadistica.id) == null
        assert response.redirectedUrl == '/estadistica/list'
    }
}
