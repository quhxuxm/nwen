export class CommandContext {
}

export class Command {
  name: string;
  value: string;
  showUi: boolean;
  rangeCommand: boolean;
  clearContext: boolean;
  context: CommandContext;
  callback: (affectedStartNode: Node, affectedEndNode: Node) => void;

  constructor() {
    this.name = null;
    this.value = null;
    this.showUi = false;
    this.context = new CommandContext();
    this.callback = null;
    this.clearContext = true;
    this.rangeCommand = true;
  }
}
