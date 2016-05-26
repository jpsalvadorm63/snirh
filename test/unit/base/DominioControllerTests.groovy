package base



import org.junit.*
import grails.test.mixin.*

@TestFor(DominioController)
@Mock(Dominio)
class DominioControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/dominio/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.dominioInstanceList.size() == 0
        assert model.dominioInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.dominioInstance != null
    }

    void testSave() {
        controller.save()

        assert model.dominioInstance != null
        assert view == '/dominio/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/dominio/show/1'
        assert controller.flash.message != null
        assert Dominio.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/dominio/list'

        populateValidParams(params)
        def dominio = new Dominio(params)

        assert dominio.save() != null

        params.id = dominio.id

        def model = controller.show()

        assert model.dominioInstance == dominio
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/dominio/list'

        populateValidParams(params)
        def dominio = new Dominio(params)

        assert dominio.save() != null

        params.id = dominio.id

        def model = controller.edit()

        assert model.dominioInstance == dominio
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/dominio/list'

        response.reset()

        populateValidParams(params)
        def dominio = new Dominio(params)

        assert dominio.save() != null

        // test invalid parameters in update
        params.id = dominio.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/dominio/edit"
        assert model.dominioInstance != null

        dominio.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/dominio/show/$dominio.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        dominio.clearErrors()

        populateValidParams(params)
        params.id = dominio.id
        params.version = -1
        controller.update()

        assert view == "/dominio/edit"
        assert model.dominioInstance != null
        assert model.dominioInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/dominio/list'

        response.reset()

        populateValidParams(params)
        def dominio = new Dominio(params)

        assert dominio.save() != null
        assert Dominio.count() == 1

        params.id = dominio.id

        controller.delete()

        assert Dominio.count() == 0
        assert Dominio.get(dominio.id) == null
        assert response.redirectedUrl == '/dominio/list'
    }
}
