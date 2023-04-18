import { ListItem, Paper, Stack, Typography } from "@mui/material";
import PropTypes from "prop-types";
import { useNavigate } from "react-router-dom";

export default function UserWordItem({ userWordData }) {
  const navigate = useNavigate();

  return (
    <ListItem
      disablePadding
      sx={{
        mb: 1,
        "&:hover": {
          cursor: "pointer",
        },
      }}
      onClick={() => {
        navigate(`/profile/words/${userWordData.wordId}`);
      }}
    >
      <Paper square sx={{ width: 1 }}>
        <Stack sx={{ padding: 1, width: 1 }}>
          <Stack direction="row" alignItems="flex-end">
            <Typography variant="h6" sx={{ display: "flex", flexGrow: 1 }}>
              {userWordData.word}
            </Typography>
            <Typography
              variant="subtitle1"
              display="inline"
              fontStyle="italic"
              sx={{ ml: 1 }}
            >
              {userWordData.category}
            </Typography>
          </Stack>

          <Typography variant="subtitle2">
            {userWordData.isLearned
              ? "Learned"
              : `Current guess streak: ${userWordData.guessStreak}`}
          </Typography>
        </Stack>
      </Paper>
    </ListItem>
  );
}

UserWordItem.propTypes = {
  userWordData: PropTypes.shape({
    wordId: PropTypes.number,
    word: PropTypes.string,
    isLearned: PropTypes.bool,
    guessStreak: PropTypes.number,
    category: PropTypes.string,
  }),
};
