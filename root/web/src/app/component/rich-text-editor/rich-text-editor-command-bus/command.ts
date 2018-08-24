
export class CommandContext {
  range: Range;
}

export class Command {
  name: string;
  value: string;
  showUi: boolean;
  context: CommandContext;
  callback: (currentSelection: Selection) => void;

  constructor() {
    this.name = null;
    this.value = null;
    this.showUi = false;
    this.context = new CommandContext();
    this.callback = null;
  }
}
