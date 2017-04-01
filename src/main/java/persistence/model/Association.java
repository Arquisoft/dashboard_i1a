package persistence.model;

public class Association {

	public static class Votation {

		public static void link(User User, Vote vote, Votable votable) {
			vote._setVotable(votable);
			vote._setUser(User);

			votable._getVotes().add(vote);
			User._getVotes().add(vote);
		}

		public static void unlink(User User, Vote vote, Proposal proposal) {
			proposal._getVotes().remove(vote);
			User._getVotes().remove(vote);

			vote._setVotable(null);
			vote._setUser(null);

		}
	}

	public static class MakeProposal {

		public static void link(User user, Proposal proposal) {
			proposal.setUser(user);
			user._getProposals().add(proposal);
		}

		public static void unlink(User user, Proposal proposal) {
			user._getProposals().remove(proposal);
			proposal.setUser(null);
		}
	}

	public static class MakeComment {

		public static void link(User User, Comment comment, Proposal proposal) {
			comment._setProposal(proposal);
			comment._setUser(User);

			proposal._getComments().add(comment);
			User._getComments().add(comment);
		}

		public static void unlink(User User, Comment comment, Proposal proposal) {
			proposal._getComments().remove(comment);
			User._getComments().remove(comment);

			comment._setProposal(null);
			comment._setUser(null);
		}
	}
}