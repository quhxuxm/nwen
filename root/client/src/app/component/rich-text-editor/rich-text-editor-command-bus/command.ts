export class CommandContext {
  range: Range;
}

export type CommandCallbackType = (editorContainerElement: HTMLElement, currentSelection: Selection) => void;

export class Command {
  name: string;
  value: string;
  showUi: boolean;
  context: CommandContext;
  callback: CommandCallbackType;

  constructor() {
    this.name = null;
    this.value = null;
    this.showUi = false;
    this.context = new CommandContext();
    this.callback = null;
  }
}
