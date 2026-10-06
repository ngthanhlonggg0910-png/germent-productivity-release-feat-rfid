import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProRefinalCCComponentsPage, ProRefinalCCDeleteDialog, ProRefinalCCUpdatePage } from './pro-refinal-cc.page-object';

const expect = chai.expect;

describe('ProRefinalCC e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proRefinalCCComponentsPage: ProRefinalCCComponentsPage;
  let proRefinalCCUpdatePage: ProRefinalCCUpdatePage;
  let proRefinalCCDeleteDialog: ProRefinalCCDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProRefinalCCS', async () => {
    await navBarPage.goToEntity('pro-refinal-cc');
    proRefinalCCComponentsPage = new ProRefinalCCComponentsPage();
    await browser.wait(ec.visibilityOf(proRefinalCCComponentsPage.title), 5000);
    expect(await proRefinalCCComponentsPage.getTitle()).to.eq('ctApp.proRefinalCC.home.title');
  });

  it('should load create ProRefinalCC page', async () => {
    await proRefinalCCComponentsPage.clickOnCreateButton();
    proRefinalCCUpdatePage = new ProRefinalCCUpdatePage();
    expect(await proRefinalCCUpdatePage.getPageTitle()).to.eq('ctApp.proRefinalCC.home.createOrEditLabel');
    await proRefinalCCUpdatePage.cancel();
  });

  it('should create and save ProRefinalCCS', async () => {
    const nbButtonsBeforeCreate = await proRefinalCCComponentsPage.countDeleteButtons();

    await proRefinalCCComponentsPage.clickOnCreateButton();
    await promise.all([
      proRefinalCCUpdatePage.setKhxhIdInput('5'),
      proRefinalCCUpdatePage.setMaHangInput('maHang'),
      proRefinalCCUpdatePage.setPoNoInput('poNo'),
      proRefinalCCUpdatePage.setNgayKiemFinalInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      proRefinalCCUpdatePage.setTenChuyenInput('tenChuyen'),
      proRefinalCCUpdatePage.setNgayGioKiemInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      proRefinalCCUpdatePage.setSoLuongKiemAqlInput('5'),
      proRefinalCCUpdatePage.setSoLuongInput('5'),
      proRefinalCCUpdatePage.setDoAmInput('5'),
      proRefinalCCUpdatePage.setTongSoLoiInput('5'),
      proRefinalCCUpdatePage.setSoThuTuThungInput('soThuTuThung'),
      proRefinalCCUpdatePage.setDoKimMdInput('doKimMd'),
      proRefinalCCUpdatePage.setAqlInput('aql'),
      proRefinalCCUpdatePage.setMaxLoiChoPhepInput('5'),
      proRefinalCCUpdatePage.setNguoiKiemInput('nguoiKiem')
    ]);
    expect(await proRefinalCCUpdatePage.getKhxhIdInput()).to.eq('5', 'Expected khxhId value to be equals to 5');
    expect(await proRefinalCCUpdatePage.getMaHangInput()).to.eq('maHang', 'Expected MaHang value to be equals to maHang');
    expect(await proRefinalCCUpdatePage.getPoNoInput()).to.eq('poNo', 'Expected PoNo value to be equals to poNo');
    expect(await proRefinalCCUpdatePage.getNgayKiemFinalInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ngayKiemFinal value to be equals to 2000-12-31'
    );
    expect(await proRefinalCCUpdatePage.getTenChuyenInput()).to.eq('tenChuyen', 'Expected TenChuyen value to be equals to tenChuyen');
    expect(await proRefinalCCUpdatePage.getNgayGioKiemInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ngayGioKiem value to be equals to 2000-12-31'
    );
    expect(await proRefinalCCUpdatePage.getSoLuongKiemAqlInput()).to.eq('5', 'Expected soLuongKiemAql value to be equals to 5');
    expect(await proRefinalCCUpdatePage.getSoLuongInput()).to.eq('5', 'Expected soLuong value to be equals to 5');
    expect(await proRefinalCCUpdatePage.getDoAmInput()).to.eq('5', 'Expected doAm value to be equals to 5');
    expect(await proRefinalCCUpdatePage.getTongSoLoiInput()).to.eq('5', 'Expected tongSoLoi value to be equals to 5');
    expect(await proRefinalCCUpdatePage.getSoThuTuThungInput()).to.eq(
      'soThuTuThung',
      'Expected SoThuTuThung value to be equals to soThuTuThung'
    );
    expect(await proRefinalCCUpdatePage.getDoKimMdInput()).to.eq('doKimMd', 'Expected DoKimMd value to be equals to doKimMd');
    expect(await proRefinalCCUpdatePage.getAqlInput()).to.eq('aql', 'Expected Aql value to be equals to aql');
    expect(await proRefinalCCUpdatePage.getMaxLoiChoPhepInput()).to.eq('5', 'Expected maxLoiChoPhep value to be equals to 5');
    const selectedCompleted = proRefinalCCUpdatePage.getCompletedInput();
    if (await selectedCompleted.isSelected()) {
      await proRefinalCCUpdatePage.getCompletedInput().click();
      expect(await proRefinalCCUpdatePage.getCompletedInput().isSelected(), 'Expected completed not to be selected').to.be.false;
    } else {
      await proRefinalCCUpdatePage.getCompletedInput().click();
      expect(await proRefinalCCUpdatePage.getCompletedInput().isSelected(), 'Expected completed to be selected').to.be.true;
    }
    expect(await proRefinalCCUpdatePage.getNguoiKiemInput()).to.eq('nguoiKiem', 'Expected NguoiKiem value to be equals to nguoiKiem');
    await proRefinalCCUpdatePage.save();
    expect(await proRefinalCCUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proRefinalCCComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProRefinalCC', async () => {
    const nbButtonsBeforeDelete = await proRefinalCCComponentsPage.countDeleteButtons();
    await proRefinalCCComponentsPage.clickOnLastDeleteButton();

    proRefinalCCDeleteDialog = new ProRefinalCCDeleteDialog();
    expect(await proRefinalCCDeleteDialog.getDialogTitle()).to.eq('ctApp.proRefinalCC.delete.question');
    await proRefinalCCDeleteDialog.clickOnConfirmButton();

    expect(await proRefinalCCComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
