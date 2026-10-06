import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { QuyCachNPLComponentsPage, QuyCachNPLDeleteDialog, QuyCachNPLUpdatePage } from './quy-cach-npl.page-object';

const expect = chai.expect;

describe('QuyCachNPL e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let quyCachNPLComponentsPage: QuyCachNPLComponentsPage;
  let quyCachNPLUpdatePage: QuyCachNPLUpdatePage;
  let quyCachNPLDeleteDialog: QuyCachNPLDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load QuyCachNPLS', async () => {
    await navBarPage.goToEntity('quy-cach-npl');
    quyCachNPLComponentsPage = new QuyCachNPLComponentsPage();
    await browser.wait(ec.visibilityOf(quyCachNPLComponentsPage.title), 5000);
    expect(await quyCachNPLComponentsPage.getTitle()).to.eq('ctApp.quyCachNPL.home.title');
  });

  it('should load create QuyCachNPL page', async () => {
    await quyCachNPLComponentsPage.clickOnCreateButton();
    quyCachNPLUpdatePage = new QuyCachNPLUpdatePage();
    expect(await quyCachNPLUpdatePage.getPageTitle()).to.eq('ctApp.quyCachNPL.home.createOrEditLabel');
    await quyCachNPLUpdatePage.cancel();
  });

  it('should create and save QuyCachNPLS', async () => {
    const nbButtonsBeforeCreate = await quyCachNPLComponentsPage.countDeleteButtons();

    await quyCachNPLComponentsPage.clickOnCreateButton();
    await promise.all([
      quyCachNPLUpdatePage.setTenNPLInput('tenNPL'),
      quyCachNPLUpdatePage.setMaNPLInput('maNPL'),
      quyCachNPLUpdatePage.setNguoiTaoInput('nguoiTao'),
      quyCachNPLUpdatePage.setNgayTaoInput('ngayTao'),
      quyCachNPLUpdatePage.setUserIdInput('5')
    ]);
    expect(await quyCachNPLUpdatePage.getTenNPLInput()).to.eq('tenNPL', 'Expected TenNPL value to be equals to tenNPL');
    expect(await quyCachNPLUpdatePage.getMaNPLInput()).to.eq('maNPL', 'Expected MaNPL value to be equals to maNPL');
    expect(await quyCachNPLUpdatePage.getNguoiTaoInput()).to.eq('nguoiTao', 'Expected NguoiTao value to be equals to nguoiTao');
    expect(await quyCachNPLUpdatePage.getNgayTaoInput()).to.eq('ngayTao', 'Expected NgayTao value to be equals to ngayTao');
    expect(await quyCachNPLUpdatePage.getUserIdInput()).to.eq('5', 'Expected userId value to be equals to 5');
    await quyCachNPLUpdatePage.save();
    expect(await quyCachNPLUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await quyCachNPLComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last QuyCachNPL', async () => {
    const nbButtonsBeforeDelete = await quyCachNPLComponentsPage.countDeleteButtons();
    await quyCachNPLComponentsPage.clickOnLastDeleteButton();

    quyCachNPLDeleteDialog = new QuyCachNPLDeleteDialog();
    expect(await quyCachNPLDeleteDialog.getDialogTitle()).to.eq('ctApp.quyCachNPL.delete.question');
    await quyCachNPLDeleteDialog.clickOnConfirmButton();

    expect(await quyCachNPLComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
