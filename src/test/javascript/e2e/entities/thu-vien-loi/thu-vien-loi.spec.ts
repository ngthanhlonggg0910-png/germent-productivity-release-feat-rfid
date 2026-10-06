import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ThuVienLoiComponentsPage, ThuVienLoiDeleteDialog, ThuVienLoiUpdatePage } from './thu-vien-loi.page-object';

const expect = chai.expect;

describe('ThuVienLoi e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let thuVienLoiComponentsPage: ThuVienLoiComponentsPage;
  let thuVienLoiUpdatePage: ThuVienLoiUpdatePage;
  let thuVienLoiDeleteDialog: ThuVienLoiDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ThuVienLois', async () => {
    await navBarPage.goToEntity('thu-vien-loi');
    thuVienLoiComponentsPage = new ThuVienLoiComponentsPage();
    await browser.wait(ec.visibilityOf(thuVienLoiComponentsPage.title), 5000);
    expect(await thuVienLoiComponentsPage.getTitle()).to.eq('ctApp.thuVienLoi.home.title');
  });

  it('should load create ThuVienLoi page', async () => {
    await thuVienLoiComponentsPage.clickOnCreateButton();
    thuVienLoiUpdatePage = new ThuVienLoiUpdatePage();
    expect(await thuVienLoiUpdatePage.getPageTitle()).to.eq('ctApp.thuVienLoi.home.createOrEditLabel');
    await thuVienLoiUpdatePage.cancel();
  });

  it('should create and save ThuVienLois', async () => {
    const nbButtonsBeforeCreate = await thuVienLoiComponentsPage.countDeleteButtons();

    await thuVienLoiComponentsPage.clickOnCreateButton();
    await promise.all([
      thuVienLoiUpdatePage.setMaLoiInput('maLoi'),
      thuVienLoiUpdatePage.setTenLoiInput('tenLoi'),
      thuVienLoiUpdatePage.setNguoiTaoInput('nguoiTao'),
      thuVienLoiUpdatePage.setNgayTaoInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      thuVienLoiUpdatePage.setUserIdInput('5')
    ]);
    expect(await thuVienLoiUpdatePage.getMaLoiInput()).to.eq('maLoi', 'Expected MaLoi value to be equals to maLoi');
    expect(await thuVienLoiUpdatePage.getTenLoiInput()).to.eq('tenLoi', 'Expected TenLoi value to be equals to tenLoi');
    expect(await thuVienLoiUpdatePage.getNguoiTaoInput()).to.eq('nguoiTao', 'Expected NguoiTao value to be equals to nguoiTao');
    expect(await thuVienLoiUpdatePage.getNgayTaoInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ngayTao value to be equals to 2000-12-31'
    );
    expect(await thuVienLoiUpdatePage.getUserIdInput()).to.eq('5', 'Expected userId value to be equals to 5');
    await thuVienLoiUpdatePage.save();
    expect(await thuVienLoiUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await thuVienLoiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ThuVienLoi', async () => {
    const nbButtonsBeforeDelete = await thuVienLoiComponentsPage.countDeleteButtons();
    await thuVienLoiComponentsPage.clickOnLastDeleteButton();

    thuVienLoiDeleteDialog = new ThuVienLoiDeleteDialog();
    expect(await thuVienLoiDeleteDialog.getDialogTitle()).to.eq('ctApp.thuVienLoi.delete.question');
    await thuVienLoiDeleteDialog.clickOnConfirmButton();

    expect(await thuVienLoiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
