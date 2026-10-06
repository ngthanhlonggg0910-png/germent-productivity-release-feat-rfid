import { element, by } from 'protractor';

export class CauHinhZaloComponentsPage {
  title = element.all(by.css('jhi-cau-hinh-zalo div h2#page-heading span')).first();

  async getTitle() {
    return this.title.getAttribute('jhiTranslate');
  }
}
