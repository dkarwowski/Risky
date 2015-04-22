package risky.connect;

public abstract class Packet {
//change enum to ints
	public enum PacketTypes {
		CMD_INVALID(-1), CMD_LOGIN (0), CMD_MOVE(1), CMD_CHANGE_TURN(2), CMD_BATTLE(3), CMD_CAPTURE(4), 
		CMD_LOSE_BATTLE(5), CMD_WIN_BATTLE(6), CMD_GET_RESOURCES(7), CMD_LOSE_RESOURCES(8),
		CMD_LOSE_GAME(9), CMD_WIN_GAME(10);

		private int packetID;
		
		PacketTypes(int packetid) {
			this.packetID = packetid;
		}
		
		public int getID(){
			return packetID;
		}
		
		public String getName() {
			return this.name();
		}
	}
	private int packetID;
	
    private Object[] data; // associate packetid with MplayPlayer - desired action

	public Packet(int packetid) {
		this.packetID = packetid;
	}

	public static PacketTypes lookupPacket(int packetid){
		for(PacketTypes p: PacketTypes.values()) {
			if(p.getID()== packetid) {
				return p;
			}
		}
		return PacketTypes.CMD_INVALID;
	}
	
	public String getName() {
		for (PacketTypes p : PacketTypes.values()) { 
			if(p.getID() == packetID) {
				return p.getName();
			}
		}
		return PacketTypes.CMD_INVALID.name();
	}
	public void actionExecute(){
		
		
		// needs to return action.playerID;
		
	}
}
