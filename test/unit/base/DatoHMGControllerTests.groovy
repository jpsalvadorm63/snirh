package base



import org.junit.*
import grails.test.mixin.*

@TestFor(DatoHMGController)
@Mock(DatoHMG)
class DatoHMGControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/datoHMG/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.datoHMGInstanceList.size() == 0
        assert model.datoHMGInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.datoHMGInstance != null
    }

    void testSave() {
        controller.save()

        assert model.datoHMGInstance != null
        assert view == '/datoHMG/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/datoHMG/show/1'
        assert controller.flash.message != null
        assert DatoHMG.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/datoHMG/list'

        populateValidParams(params)
        def datoHMG = new DatoHMG(params)

        assert datoHMG.save() != null

        params.id = datoHMG.id

        def model = controller.show()

        assert model.datoHMGInstance == datoHMG
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/datoHMG/list'

        populateValidParams(params)
        def datoHMG = new DatoHMG(params)

        assert datoHMG.save() != null

        params.id = datoHMG.id

        def model = controller.edit()

        assert model.datoHMGInstance == datoHMG
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/datoHMG/list'

        response.reset()

        populateValidParams(params)
        def datoHMG = new DatoHMG(params)

        assert datoHMG.save() != null

        // test invalid parameters in update
        params.id = datoHMG.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/datoHMG/edit"
        assert model.datoHMGInstance != null

        datoHMG.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/datoHMG/show/$datoHMG.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        datoHMG.clearErrors()

        populateValidParams(params)
        params.id = datoHMG.id
        params.version = -1
        controller.update()

        assert view == "/datoHMG/edit"
        assert model.datoHMGInstance != null
        assert model.datoHMGInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/datoHMG/list'

        response.reset()

        populateValidParams(params)
        def datoHMG = new DatoHMG(params)

        assert datoHMG.save() != null
        assert DatoHMG.count() == 1

        params.id = datoHMG.id

        controller.delete()

        assert DatoHMG.count() == 0
        assert DatoHMG.get(datoHMG.id) == null
        assert response.redirectedUrl == '/datoHMG/list'
    }
}
