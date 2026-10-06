import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  ProKeHoachSanXuatComponentsPage,
  ProKeHoachSanXuatDeleteDialog,
  ProKeHoachSanXuatUpdatePage
} from './pro-ke-hoach-san-xuat.page-object';

const expect = chai.expect;

describe('ProKeHoachSanXuat e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proKeHoachSanXuatComponentsPage: ProKeHoachSanXuatComponentsPage;
  let proKeHoachSanXuatUpdatePage: ProKeHoachSanXuatUpdatePage;
  let proKeHoachSanXuatDeleteDialog: ProKeHoachSanXuatDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProKeHoachSanXuats', async () => {
    await navBarPage.goToEntity('pro-ke-hoach-san-xuat');
    proKeHoachSanXuatComponentsPage = new ProKeHoachSanXuatComponentsPage();
    await browser.wait(ec.visibilityOf(proKeHoachSanXuatComponentsPage.title), 5000);
    expect(await proKeHoachSanXuatComponentsPage.getTitle()).to.eq('ctApp.proKeHoachSanXuat.home.title');
  });

  it('should load create ProKeHoachSanXuat page', async () => {
    await proKeHoachSanXuatComponentsPage.clickOnCreateButton();
    proKeHoachSanXuatUpdatePage = new ProKeHoachSanXuatUpdatePage();
    expect(await proKeHoachSanXuatUpdatePage.getPageTitle()).to.eq('ctApp.proKeHoachSanXuat.home.createOrEditLabel');
    await proKeHoachSanXuatUpdatePage.cancel();
  });

  it('should create and save ProKeHoachSanXuats', async () => {
    const nbButtonsBeforeCreate = await proKeHoachSanXuatComponentsPage.countDeleteButtons();

    await proKeHoachSanXuatComponentsPage.clickOnCreateButton();
    await promise.all([
      proKeHoachSanXuatUpdatePage.setNgaySanXuatInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      proKeHoachSanXuatUpdatePage.setSoLuongYeuCauInput('5'),
      proKeHoachSanXuatUpdatePage.setTinhTrangInput('5'),
      proKeHoachSanXuatUpdatePage.setGhiChuInput('ghiChu'),
      proKeHoachSanXuatUpdatePage.setSamInput('5'),
      proKeHoachSanXuatUpdatePage.setLotNoInput('5'),
      proKeHoachSanXuatUpdatePage.proChuyenSelectLastOption(),
      proKeHoachSanXuatUpdatePage.proSanPhamSelectLastOption()
    ]);
    expect(await proKeHoachSanXuatUpdatePage.getNgaySanXuatInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ngaySanXuat value to be equals to 2000-12-31'
    );
    expect(await proKeHoachSanXuatUpdatePage.getSoLuongYeuCauInput()).to.eq('5', 'Expected soLuongYeuCau value to be equals to 5');
    expect(await proKeHoachSanXuatUpdatePage.getTinhTrangInput()).to.eq('5', 'Expected tinhTrang value to be equals to 5');
    expect(await proKeHoachSanXuatUpdatePage.getGhiChuInput()).to.eq('ghiChu', 'Expected GhiChu value to be equals to ghiChu');
    expect(await proKeHoachSanXuatUpdatePage.getSamInput()).to.eq('5', 'Expected sam value to be equals to 5');
    expect(await proKeHoachSanXuatUpdatePage.getLotNoInput()).to.eq('5', 'Expected lotNo value to be equals to 5');
    await proKeHoachSanXuatUpdatePage.save();
    expect(await proKeHoachSanXuatUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proKeHoachSanXuatComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ProKeHoachSanXuat', async () => {
    const nbButtonsBeforeDelete = await proKeHoachSanXuatComponentsPage.countDeleteButtons();
    await proKeHoachSanXuatComponentsPage.clickOnLastDeleteButton();

    proKeHoachSanXuatDeleteDialog = new ProKeHoachSanXuatDeleteDialog();
    expect(await proKeHoachSanXuatDeleteDialog.getDialogTitle()).to.eq('ctApp.proKeHoachSanXuat.delete.question');
    await proKeHoachSanXuatDeleteDialog.clickOnConfirmButton();

    expect(await proKeHoachSanXuatComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
