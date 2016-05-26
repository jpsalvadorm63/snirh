package base



import org.junit.*
import grails.test.mixin.*

@TestFor(OperativoController)
@Mock(Operativo)
class OperativoControllerTests {

  def populateValidParams(params) {
    assert params != null
    // TODO: Populate valid properties like...
    //params["name"] = 'someValidName'
  }

  void testIndex() {
    controller.index()
    assert "/operativo/list" == response.redirectedUrl
  }

  void testList() {

    def model = controller.list()

    assert model.operativoInstanceList.size() == 0
    assert model.operativoInstanceTotal == 0
  }

  void testCreate() {
    def model = controller.create()

    assert model.operativoInstance != null
  }

  void testSave() {
    controller.save()

    assert model.operativoInstance != null
    assert view == '/operativo/create'

    response.reset()

    populateValidParams(params)
    controller.save()

    assert response.redirectedUrl == '/operativo/show/1'
    assert controller.flash.message != null
    assert Operativo.count() == 1
  }

  void testShow() {
    controller.show()

    assert flash.message != null
    assert response.redirectedUrl == '/operativo/list'

    populateValidParams(params)
    def operativo = new Operativo(params)

    assert operativo.save() != null

    params.id = operativo.id

    def model = controller.show()

    assert model.operativoInstance == operativo
  }

  void testEdit() {
    controller.edit()

    assert flash.message != null
    assert response.redirectedUrl == '/operativo/list'

    populateValidParams(params)
    def operativo = new Operativo(params)

    assert operativo.save() != null

    params.id = operativo.id

    def model = controller.edit()

    assert model.operativoInstance == operativo
  }

  void testUpdate() {
    controller.update()

    assert flash.message != null
    assert response.redirectedUrl == '/operativo/list'

    response.reset()

    populateValidParams(params)
    def operativo = new Operativo(params)

    assert operativo.save() != null

    // test invalid parameters in update
    params.id = operativo.id
    //TODO: add invalid values to params object

    controller.update()

    assert view == "/operativo/edit"
    assert model.operativoInstance != null

    operativo.clearErrors()

    populateValidParams(params)
    controller.update()

    assert response.redirectedUrl == "/operativo/show/$operativo.id"
    assert flash.message != null

    //test outdated version number
    response.reset()
    operativo.clearErrors()

    populateValidParams(params)
    params.id = operativo.id
    params.version = -1
    controller.update()

    assert view == "/operativo/edit"
    assert model.operativoInstance != null
    assert model.operativoInstance.errors.getFieldError('version')
    assert flash.message != null
  }

  void testDelete() {
    controller.delete()
    assert flash.message != null
    assert response.redirectedUrl == '/operativo/list'

    response.reset()

    populateValidParams(params)
    def operativo = new Operativo(params)

    assert operativo.save() != null
    assert Operativo.count() == 1

    params.id = operativo.id

    controller.delete()

    assert Operativo.count() == 0
    assert Operativo.get(operativo.id) == null
    assert response.redirectedUrl == '/operativo/list'
  }
}
